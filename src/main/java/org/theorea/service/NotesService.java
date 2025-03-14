package org.theorea.service;

import org.theorea.model.dto.NewNoteDto;
import org.theorea.model.dto.NoteDto;
import org.theorea.model.dto.UpdateNoteDto;

import java.util.List;

public interface NotesService {

    List<NoteDto> getAllNotes();

    NoteDto getNoteById(Long id);

    NoteDto updateNote(UpdateNoteDto updateDto);

    NoteDto createNote(NewNoteDto newNoteDto);

    void delete(Long id);
}
