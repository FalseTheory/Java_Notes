package org.theorea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.theorea.model.dto.NewNoteDto;
import org.theorea.model.dto.NoteDto;
import org.theorea.model.dto.UpdateNoteDto;
import org.theorea.service.NotesService;

import java.util.List;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService service;

    @GetMapping
    public String getAllNotes(Model model) {
        List<NoteDto> notes = service.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("note", new NewNoteDto());
        return "notes/form";
    }

    @PostMapping("/new")
    public String createNote(@ModelAttribute NewNoteDto newNoteDto) {
        service.createNote(newNoteDto);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        NoteDto noteDto = service.getNoteById(id);
        model.addAttribute("note", noteDto);
        return "notes/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute UpdateNoteDto updateNoteDto) {
        updateNoteDto.setId(id);
        service.updateNote(updateNoteDto);
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}") // Использую только методы GET и POST поскольку формы html не поддерживают другие методы без JavaScript
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/notes";
    }
}
