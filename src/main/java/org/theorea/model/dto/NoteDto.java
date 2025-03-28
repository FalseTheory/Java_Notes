package org.theorea.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoteDto  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
