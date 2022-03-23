package fr.gouv.culture.francetransfert.core.enums;

public enum CheckMailKeysEnum {

	UUID("uuid"), SEND_AT("sendAt"), DELAY("delay"), PENDING("pending");

	CheckMailKeysEnum(String key) {
		this.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String key;

}
