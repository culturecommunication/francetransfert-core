package fr.gouv.culture.francetransfert.utils;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kosprov.jargon2.api.Jargon2.Hasher;
import com.kosprov.jargon2.api.Jargon2.Type;

import fr.gouv.culture.francetransfert.exception.StatException;

@Service
public class Base64CryptoService {

	@Value("${password.minsize}")
	private int passwordMinSize;
	@Value("${password.maxsize}")
	private int passwordMaxSize;
	@Value("${password.lower.min}")
	private int passwordLowerMin;
	@Value("${password.upper.min}")
	private int passwordUpperMin;
	@Value("${password.special.min}")
	private int passwordSpecialMin;
	@Value("${password.digit.min}")
	private int passwordDigitMin;
	@Value("${password.special.list}")
	private String passwordSpecialList;
	@Value("${password.salt}")
	private String passwordSalt;

	private final static Type TYPE = Type.ARGON2d;
	private final static int MEMORY_COST = 65536;
	private final static int TIME_COST = 3;
	private final static int PARALLELISM = 4;
	private final static int HASH_LENGTH = 16;

	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;
	private static final int SALT_LENGTH_BYTE = 16;

	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

	private final static Hasher HASHER = jargon2Hasher().type(TYPE).memoryCost(MEMORY_COST).timeCost(TIME_COST)
			.parallelism(PARALLELISM).hashLength(HASH_LENGTH);

	private static final Logger LOGGER = LoggerFactory.getLogger(Base64CryptoService.class);

	public String base64Decoder(String string) throws UnsupportedEncodingException {
		byte[] asBytes = Base64.getUrlDecoder().decode(string);
		return new String(asBytes, "utf-8");
	}

	public String base64Encoder(String string) throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(string.getBytes("utf-8"));
	}

	/**
	 *
	 * @param password
	 * @return Set the salt and password to calculate the raw hash
	 */
	public String calculatePasswordHashed(String password) {
		byte[] rawHash = HASHER.salt(getSaltBytes()).password(password.getBytes()).rawHash();
		return new String(rawHash);
	}

	public String encodedHash(String password) {
		String encoded = HASHER.salt(getSaltBytes()).password(password.getBytes()).encodedHash();
		String[] splited = encoded.split("\\$");
		return splited[splited.length - 1];
	}

	private SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password, salt, 655, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public String aesEncrypt(String pText)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		// Random salt & iv
		byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);
		byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

		// secret key from password
		SecretKey aesKeyFromPassword = getAESKeyFromPassword(getSaltChars(), salt);

		Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

		// ASE-GCM needs GCMParameterSpec
		cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

		byte[] cipherText = cipher.doFinal(pText.getBytes());

		// prefix IV and Salt to cipher text
		byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv).put(salt)
				.put(cipherText).array();

		// string representation, base64, send this string to other for decryption.
		return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
	}

	public String aesDecrypt(String cText) throws StatException {
		byte[] decode = Base64.getDecoder().decode(cText.getBytes(StandardCharsets.UTF_8));

		// get back the iv and salt from the cipher text
		ByteBuffer bb = ByteBuffer.wrap(decode);

		byte[] iv = new byte[IV_LENGTH_BYTE];
		bb.get(iv);

		byte[] salt = new byte[SALT_LENGTH_BYTE];
		bb.get(salt);

		byte[] cipherText = new byte[bb.remaining()];
		bb.get(cipherText);

		// get back the aes key from the same password and salt
		try {
			SecretKey aesKeyFromPassword = getAESKeyFromPassword(getSaltChars(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText, StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException
				| InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException e) {
			throw new StatException("Error while decrypt", e);
		}
	}

	private byte[] getRandomNonce(int length) {
		byte[] nonce = new byte[length];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	private List<Rule> getPasswordRules() {
		List<Rule> rules = new ArrayList<>();
		// Rule 1: Password length should be in between
		// 8 and 16 characters
		rules.add(new LengthRule(passwordMinSize, passwordMaxSize));
		// Rule 2: No whitespace allowed
		rules.add(new WhitespaceRule());
		// Rule 3.a: At least one Upper-case character
		rules.add(new CharacterRule(EnglishCharacterData.UpperCase, passwordLowerMin));
		// Rule 3.b: At least one Lower-case character
		rules.add(new CharacterRule(EnglishCharacterData.LowerCase, passwordUpperMin));
		// Rule 3.c: At least one digit
		rules.add(new CharacterRule(EnglishCharacterData.Digit, passwordDigitMin));
		// Rule 3.d: At least one special character
		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return "412";
			}

			public String getCharacters() {
				return passwordSpecialList;
			}
		};
		rules.add(new CharacterRule(specialChars, passwordSpecialMin));

		return rules;

	}

	public String generatePassword(int count) {
		LOGGER.info("Generate new password");
		PasswordGenerator gen = new PasswordGenerator();
		CharacterRule[] rule = getPasswordRules().subList(2, getPasswordRules().size()).toArray(new CharacterRule[0]);
		String password = "";
		try {
			password = gen.generatePassword(passwordMinSize, rule);
		} catch (Exception e) {
			LOGGER.info("Generated Password KO ", e);
			if (count < 5) {
				LOGGER.info("Retry Password");
				return generatePassword(count + 1);
			}
		}
		return password;
	}

	public Boolean validatePassword(String password) {
		LOGGER.info("Check if password valid");
		if (password == null || StringUtils.isEmpty(password)) {
			LOGGER.info("Empty password");
			return false;
		} else {
			try {
				PasswordValidator validator = new PasswordValidator(getPasswordRules());
				RuleResult result = validator.validate(new PasswordData(password));

				if (result.isValid()) {
					LOGGER.info("valid password");
					return true;
				} else {
					LOGGER.info("not valid password");
					return false;
				}
			} catch (Exception e) {
				LOGGER.info("not valid password Exception");
				return false;
			}
		}
	}

	private byte[] getSaltBytes() {
		return passwordSalt.getBytes();
	}

	private char[] getSaltChars() {
		return passwordSalt.toCharArray();
	}

}
