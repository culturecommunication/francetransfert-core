package fr.gouv.culture.francetransfert.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRecipient {
    private String id;
    private String mail;
    private String idEnclosure;
}
