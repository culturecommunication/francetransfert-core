package fr.gouv.culture.francetransfert.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FileKeysEnum {
	REL_OBJ_KEY("rel-obj-key"), SIZE("size"), MUL_NB_CHUNKS_DONE("mul-nb-chunks-done"),
	MUL_CHUNKS_ITERATION("mul-chunks-iteration");

	private String key;

	FileKeysEnum(String key) {
		this.key = key;
	}

	public static List<String> keys() {
		return Stream.of(FileKeysEnum.values()).map(e -> e.key).collect(Collectors.toList());
	}

	public String getKey() {
		return key;
	}
}
