/*
  * Copyright (c) Ministère de la Culture (2022) 
  * 
  * SPDX-License-Identifier: Apache-2.0 
  * License-Filename: LICENSE.txt 
  */

package fr.gouv.culture.francetransfert.core.model;
import java.time.LocalDate;

import fr.gouv.culture.francetransfert.core.enums.TypeStat;
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
