package fr.gouv.culture.francetransfert.core.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RateRepresentation extends StatModel {

	@Min(0)
	@Max(3)
	private int satisfaction;

	private String message;
}
