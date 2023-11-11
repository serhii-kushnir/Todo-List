package com.todolist.controller;

import com.todolist.entity.Note;
import com.todolist.service.NoteService;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public final class NoteController {
    private final NoteService noteService;
    private static final String BASE_REDIRECT = "redirect:/note/list";

    @GetMapping("/list")
    public String getAllNotes(final Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "notes";
    }

    @GetMapping("/create")
    public String createNote(final Model model) {
       model.addAttribute("note", new Note());
       return "create-note";
    }

    @PostMapping("/create")
    public String creatingNote(final @ModelAttribute("note") Note note) {
        noteService.saveAndUpdate(note);
        return BASE_REDIRECT;
    }

    @GetMapping("/edit")
    public String editNoteById(final @RequestParam("id") Long id, final Model model) {
        final Note note = noteService.findById(id);
        model.addAttribute("note", note);
        return "edit-note";
    }

    @PostMapping("/edit")
    public String saveEditingNoteById(final @ModelAttribute("note") Note note) {
        noteService.saveAndUpdate(note);
        return BASE_REDIRECT;
    }

    @PostMapping("/delete")
    public String deleteNoteById(final @RequestParam("id") Long id) {
        noteService.deleteById(id);
        return BASE_REDIRECT;
    }
}
