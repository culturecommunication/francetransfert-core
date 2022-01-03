package fr.gouv.culture.francetransfert.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenConfirmationKeysEnum {
	GUID("guid"), TIMESTAMP("timestamp");

	private String key;

	TokenConfirmationKeysEnum(String key) {
		this.key = key;
	}

	public static List<String> keys() {
		return Stream.of(TokenConfirmationKeysEnum.values()).map(e -> e.key).collect(Collectors.toList());
	}

	public String getKey() {
		return key;
	}
}
