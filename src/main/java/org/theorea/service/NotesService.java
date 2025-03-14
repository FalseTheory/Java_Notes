package org.theorea.service;

import org.theorea.model.dto.NoteDto;

import java.util.List;
import java.util.Optional;

public interface NotesService {

    List<NoteDto> getAllNotes();

    Optional<NoteDto> getNoteById(Long id);

    NoteDto updateNote(NoteDto noteDto);

    void delete(Long id);
}
