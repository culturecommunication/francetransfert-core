package fr.gouv.culture.francetransfert.model;
import java.time.LocalDate;

import fr.gouv.culture.francetransfert.enums.TypeStat;
import lombok.Data;

@Data
public class StatModel {

	protected String plis;

	protected String hashMail;

	protected String domain;

	protected LocalDate date;

	protected TypeStat type;
	
	protected String mailAdress;

}
