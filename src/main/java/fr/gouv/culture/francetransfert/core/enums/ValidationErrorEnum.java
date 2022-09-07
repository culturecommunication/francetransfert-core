/* 
 * Copyright (c) Ministère de la Culture (2022) 
 * 
 * SPDX-License-Identifier: Apache-2.0 
 * License-Filename: LICENSE.txt 
 */

/* 
 * Copyright (c) Ministère de la Culture (2022) 
 * 
 * SPDX-License-Identifier: Apache-2.0 
 * License-Filename: LICENSE.txt 
 */

package fr.gouv.culture.francetransfert.core.enums;

//---
public enum ValidationErrorEnum {

	FT01("typePli", "ERR_FT01_001", "Le type de pli est obligatoire"),
	FT02("typePli", "ERR_FT01_002",
			"La valeur fournie pour le champ typePli doit appartenir à la liste de valeur « Liste des types de pli »"),
	FT04("courrielExpediteur", "ERR_FTXX_001",
			"Le domaine de messagerie du courriel expéditeur doit être celui d’un agent de l’Etat"),
	FT05("courrielExpediteur", "ERR_FTXX_002", "Le courriel expéditeur est obligatoire"),
	FT06("courrielExpediteur", "ERR_FTXX_003", "Le courriel expéditeur doit respecter le format d’un courriel"),
	FT07("destinataires", "ERR_FT01_007",
			"Une liste de destinataires est obligatoire si le type de Pli est « courriel »"),
	FT08("destinataires.courrielDestinataire", "ERR_FT01_008",
			"Si une liste de destinataires est fournie alors au moins un destinataire doit être renseigné"),
	FT09("destinataires.courrielDestinataire", "ERR_FT01_009",
			"Le courriel destinataire doit respecter le format d’un courriel"),
	FT010("preferences.dateValidite", "ERR_FT01_010",
			"La date de fin de validité du pli doit être comprise entre J+1 et J+90 jours"),
	FT011("preferences.dateValidite", "ERR_FT01_011", "La date de fin de validité doit respecter le format d’une date"),
	FT012("preferences.motDePasse", "ERR_FT01_012",
			"Le mot de passe doit respecter les critères de robustesse et caractères autorisés : 12 caractères minimum - 20 caractères maximum - Au moins 3 lettres minuscules (a-z non accentué) - Au moins 3 lettres majuscules (A-Z non accentué) - Au moins 3 chiffres - Au moins 3 caractères spéciaux (!@#$%^&*()_-:+) - aucun caractère spécial non supporté\r\n"),
	FT014("preferences.langueCourriel", "ERR_FT01_014",
			"La valeur fournie pour le champ langueCourriel doit appartenir à la liste de valeur « Liste des langues de courriel »"),
	FT016("preferences.protectionArchive", "ERR_FT01_016",
			"Le champ protectionArchive doit respecter le format d’un booléen"),
	FT018("fichiers", "ERR_FT01_018", "Au moins un fichier doit être fourni"),
	FT019("fichiers.nomFichier", "ERR_FTXX_006", "Le nom de fichier est obligatoire"),
	FT020("fichiers.tailleFichier", "ERR_FTXX_007", "La taille de fichier est obligatoire"),
	FT021("fichiers.tailleFichier", "ERR_FT01_021",
			"La taille de chaque fichier ne peut pas dépasser 2 147 483 648 octets (2 Go)"),
	FT022("fichiers.tailleFichier", "ERR_FT01_022",
			"La taille totale du pli ne peut pas dépasser 21 474 836 480 (20 Go)"),
	FT023("fichiers.idFichier", "ERR_FTXX_005", "L’identifiant de fichier est obligatoire"),
	FT024("fichiers.cheminRelatif", "ERR_FT01_024", "Le chemin relatif d’accès au fichier est obligatoire"),

	FT204("courrielExpediteur", "ERR_FT02_004", "Le courriel expéditeur doit respecter le format d’un courriel"),
	FT205("idPli", "ERR_FTXX_004",
			"Le courriel expéditeur et l’identifiant de pli indiqués dans la requête doivent correspondre à un pli connu de France transfert"),
	FT206("idFichier", "ERR_FT02_006", "L’identifiant de fichier est obligatoire"),
	FT207("nomFichier", "ERR_FT02_007", "Le nom de fichier est obligatoire"),
	FT208("numMorceauFichier", "ERR_FT02_008", "Le numéro de morceau du fichier est obligatoire"),
	FT209("numMorceauFichier", "ERR_FT02_009",
			"Le numéro de morceau du fichier doit être inférieur ou égal au nombre total de morceaux du fichier"),
	FT2010("numMorceauFichier", "ERR_FT02_010", "Le numéro de morceau du fichier doit être un entier supérieur à 0"),
	FT2011("totalMorceauxFichier", "ERR_FT02_011", "Le nombre total de morceaux du fichier est obligatoire"),
	FT2012("totalMorceauxFichier", "ERR_FT02_012",
			"Le nombre total de morceaux du fichier doit être un entier supérieur à 0"),
	FT2013("tailleMorceauFichier", "ERR_FT02_013", "La taille du morceau du fichier est obligatoire"),
	FT2014("tailleMorceauFichier", "ERR_FT02_014", "La taille du morceau du fichier doit être un entier supérieur à 0"),
	FT2016("fichier", "ERR_FT02_016", "Le contenu du fichier est obligatoire"),
	FT2017("", "ERR_FT02_017",
			"Pour autoriser le chargement des fichiers du pli, le statut du pli doit être 000-INI ou 010-ECC"),
	FT2018("tailleFichier", "ERR_FT02_018", "La taille du fichier doit être un entier supérieur à 0"),
	FT406("", "ERR_FT04_006",
			"Pour autoriser la fourniture des métadonnées du pli, le statut du pli doit être 032-PAT"),
	FT2019("", "ERR_FT02_019",
			"L’identifiant de fichier fourni doit correspondre à un identifiant de fichier déclaré à l’initialisation du pli");

	ValidationErrorEnum(String codeChamp, String numErreur, String libelleErreur) {
		this.setCodeChamp(codeChamp);
		this.setNumErreur(numErreur);
		this.setLibelleErreur(libelleErreur);
	}

	public String getCodeChamp() {
		return codeChamp;
	}

	public String getNumErreur() {
		return numErreur;
	}

	public String getLibelleErreur() {
		return libelleErreur;
	}

	public void setCodeChamp(String codeChamp) {
		this.codeChamp = codeChamp;
	}

	public void setNumErreur(String numErreur) {
		this.numErreur = numErreur;
	}

	public void setLibelleErreur(String libelleErreur) {
		this.libelleErreur = libelleErreur;
	}

	private String codeChamp;
	private String numErreur;
	private String libelleErreur;

}
