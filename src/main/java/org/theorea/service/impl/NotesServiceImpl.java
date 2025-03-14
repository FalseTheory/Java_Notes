package org.theorea.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.theorea.mapper.NotesMapper;
import org.theorea.model.Note;
import org.theorea.model.dto.NewNoteDto;
import org.theorea.model.dto.NoteDto;
import org.theorea.model.dto.UpdateNoteDto;
import org.theorea.model.exception.NotFoundException;
import org.theorea.repository.NotesRepository;
import org.theorea.service.NotesService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotesServiceImpl implements NotesService {

    private final NotesRepository repository;
    private final NotesMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<NoteDto> getAllNotes() {
        return repository.findAll().stream()
                .map(mapper::mapToNoteDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NoteDto getNoteById(Long id) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Заметки с id - " + id + " не найдено"));

        return mapper.mapToNoteDto(note);
    }

    @Override
    public NoteDto updateNote(UpdateNoteDto updateDto) {
        Note note = repository.findById(updateDto.getId())
                .orElseThrow(() -> new NotFoundException("Заметки с id - " + updateDto.getId() + " не найдено"));
        if(updateDto.getContent()!=null) {
            note.setContent(updateDto.getContent());
        }
        if(updateDto.getTitle()!=null) {
            note.setTitle(updateDto.getTitle());
        }


        return mapper.mapToNoteDto(repository.save(note));
    }

    @Override
    public NoteDto createNote(NewNoteDto newNoteDto) {

        return mapper.mapToNoteDto(repository.save(mapper.mapToNote(newNoteDto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
