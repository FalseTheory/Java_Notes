package org.theorea.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.theorea.model.dto.NewNoteDto;
import org.theorea.model.dto.NoteDto;
import org.theorea.model.dto.UpdateNoteDto;
import org.theorea.service.NotesService;

import java.util.List;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
@Slf4j
@Validated
public class NotesController {

    private final NotesService service;

    @GetMapping
    public String getAllNotes(Model model) {
        List<NoteDto> notes = service.getAllNotes();
        log.info("retrieving all notes");
        model.addAttribute("notes", notes);
        return "notes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("note", new NewNoteDto());
        log.info("creating new note form");
        return "notes/form";
    }

    @PostMapping("/new")
    public String createNote(@ModelAttribute @Valid NewNoteDto newNoteDto) {
        service.createNote(newNoteDto);
        log.info("note created.., redirecting to main page");
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @Positive Long id, Model model) {
        NoteDto noteDto = service.getNoteById(id);
        model.addAttribute("note", noteDto);
        log.info("open editing form for node with id - {}", id);
        return "notes/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable @Positive Long id, @ModelAttribute @Valid UpdateNoteDto updateNoteDto) {
        updateNoteDto.setId(id);
        service.updateNote(updateNoteDto);
        log.info("update successfull, redirecting");
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}") // Использую только методы GET и POST поскольку формы html не поддерживают другие методы без JavaScript
    public String delete(@PathVariable Long id) {
        service.delete(id);
        log.info("deleting note with id - {}", id);
        return "redirect:/notes";
    }
}
