package org.theorea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theorea.model.Note;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
