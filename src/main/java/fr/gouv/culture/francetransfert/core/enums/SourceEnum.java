package fr.gouv.culture.francetransfert.core.enums;

public enum SourceEnum {

	PUBLIC("public"), PRIVATE("private");

	private String value;

	SourceEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}