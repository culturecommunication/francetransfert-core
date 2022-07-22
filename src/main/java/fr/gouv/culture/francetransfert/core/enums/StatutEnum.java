/* 
 * Copyright (c) Ministère de la Culture (2022) 
 * 
 * SPDX-License-Identifier: Apache-2.0 
 * License-Filename: LICENSE.txt 
 */

package fr.gouv.culture.francetransfert.core.enums;

//---
public enum StatutEnum {

	ECH("011-ECH", "Erreur lors du chargement du pli"), CHT("012-CHT", "Chargement terminé"),
	ECC("010-ECC", "En cours de chargement"), INI("000-INI", "Initialisé"), EDC("040-EDC", "Envoi des courriels"),
	PAT("042-PAT", "Prêt au téléchargement"), EEC("041-EEC", "Erreur lors de l’envoi des courriels"),
	AAV("030-AAV", "Analyse antivirale"), EAV("031-EAV", "Erreur détectée lors de l’analyse antivirale"),
	APT("032-APT", "Analyse antivirale du pli terminée");

	StatutEnum(String code, String word) {
		this.setCode(code);
		this.setWord(word);
	}

	public String getCode() {
		return code;
	}

	public String getWord() {
		return word;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private String code;
	private String word;

}
