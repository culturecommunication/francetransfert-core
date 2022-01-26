package fr.gouv.culture.francetransfert.core.enums;

public enum CaptchaTypeEnum {
	SOUND("SOUND"), IMAGE("IMAGE");

	private String value;

	CaptchaTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
