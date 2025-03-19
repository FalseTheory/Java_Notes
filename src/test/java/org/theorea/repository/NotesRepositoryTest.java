package org.theorea.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.theorea.model.Note;
import org.theorea.model.exception.NotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class NotesRepositoryTest {

    @Autowired
    private NotesRepository repository;
    public static final Long NOTE_TEST_ID = 1L;

    static Note getTestNote() {
        Note note = new Note();
        note.setTitle("Заметка");
        note.setContent("Простое содержание заметки");

        return note;
    }


    @Test
    @DisplayName("Заметка должна корректно сохраняться")
    void saveAndFindNote() {
        Note note = getTestNote();

        repository.save(note);

        List<Note> noteList = repository.findAll();
        assertThat(noteList).hasSize(1);
        assertThat(noteList.getFirst().getTitle()).isEqualTo(note.getTitle());
        assertThat(noteList.getFirst().getContent()).isEqualTo(note.getContent());

    }
    @Test
    @DisplayName("Заметка должна корректно удаляться")
    void saveAndDeleteNote() {
        Note note = getTestNote();

        repository.save(note);

        List<Note> noteList = repository.findAll();
        assertThat(noteList).hasSize(1);

        repository.deleteById(noteList.getFirst().getId());

        noteList = repository.findAll();
        assertThat(noteList).hasSize(0);
    }
    @Test
    @DisplayName("Заметка должна корректно обновляться")
    void saveAndUpdateNote() {
        Note note = getTestNote();
        note.setId(NOTE_TEST_ID);

        repository.save(note);

        assertThat(repository.findById(NOTE_TEST_ID))
                .isPresent();
        note.setTitle("Новый заголовок");
        note.setContent("Новое содержание");
        repository.save(note);

        Note updatedNote = repository.findById(NOTE_TEST_ID)
                .orElseThrow(()->new NotFoundException("Не найдено записки с id 1"));

        assertThat(note.getTitle()).isEqualTo(updatedNote.getTitle());
        assertThat(note.getContent()).isEqualTo(updatedNote.getContent());
    }
}
