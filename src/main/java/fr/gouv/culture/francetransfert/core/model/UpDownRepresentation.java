package fr.gouv.culture.francetransfert.core.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpDownRepresentation extends StatModel {

	private Set<String> receiveDomain;

	private String fileSize;

}
