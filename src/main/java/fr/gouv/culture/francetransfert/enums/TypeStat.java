package fr.gouv.culture.francetransfert.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeStat {

	UPLOAD("upload"), DOWNLOAD("download"), UPLOAD_SATISFACTION("upload_satisfaction"),
	DOWNLOAD_SATISFACTION("download_satisfaction");

	private String value;

}
