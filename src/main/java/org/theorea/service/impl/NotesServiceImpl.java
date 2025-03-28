package org.theorea.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotesServiceImpl implements NotesService {

    private final NotesRepository repository;
    private final NotesMapper mapper;


    /**
     * Функция для создания первой заметки, в случае первого запуска приложения,
     * или если база пустая
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeFirstTimeNote() {
        if (repository.count() == 0) {
            Note initNote = new Note();
            initNote.setTitle("Пример заметки");
            initNote.setContent("Hello World!");
            initNote.setCreatedAt(LocalDateTime.now());
            initNote.setUpdatedAt(LocalDateTime.now());
            repository.save(initNote);
        }
    }

    @Override
    @Cacheable("notes")
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
    @CacheEvict(value = "notes", allEntries = true)
    public NoteDto updateNote(UpdateNoteDto updateDto) {
        Note note = repository.findById(updateDto.getId())
                .orElseThrow(() -> new NotFoundException("Заметки с id - " + updateDto.getId() + " не найдено"));
        if (updateDto.getContent() != null) {
            note.setContent(updateDto.getContent());
        }
        if (updateDto.getTitle() != null) {
            note.setTitle(updateDto.getTitle());
        }


        return mapper.mapToNoteDto(repository.save(note));
    }

    @Override
    @CacheEvict(value = "notes", allEntries = true)
    public NoteDto createNote(NewNoteDto newNoteDto) {

        return mapper.mapToNoteDto(repository.save(mapper.mapToNote(newNoteDto)));
    }

    @Override
    @CacheEvict(value = "notes", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
