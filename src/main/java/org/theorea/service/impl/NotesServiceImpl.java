package org.theorea.service.impl;

import org.theorea.model.dto.NoteDto;
import org.theorea.service.NotesService;

import java.util.List;
import java.util.Optional;

public class NotesServiceImpl implements NotesService {
    @Override
    public List<NoteDto> getAllNotes() {
        return null;
    }

    @Override
    public Optional<NoteDto> getNoteById(Long id) {
        return Optional.empty();
    }

    @Override
    public NoteDto updateNote(NoteDto noteDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
