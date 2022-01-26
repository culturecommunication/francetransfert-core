package fr.gouv.culture.francetransfert.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormulaireContactData {
    private String nom;
    private String prenom;
    private String from;
    private String administration;
    private String message;
    private String subject;
}
