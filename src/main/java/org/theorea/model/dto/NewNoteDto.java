package org.theorea.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewNoteDto {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
