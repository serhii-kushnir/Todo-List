package com.todolist.controller;

import com.todolist.entity.Note;
import com.todolist.service.NoteService;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public final class NoteController {
    private final NoteService noteService;
    private static final String BASE_REDIRECT = "redirect:/note/list";
    private static final String TEMPLATE_EDIT_NOTE = "edit-note";
    private static final String TEMPLATE_CREATE_NOTE = "create-note";
    private static final String TEMPLATE_NOTES_LIST = "notes-list";

    @GetMapping("/list")
    public String getAllNotes(final @RequestParam(name = "search", required = false) String search,
                              final Model model) {
        if (search != null) {
            List<Note> searchedNotes = noteService.searchNotes(search);
            model.addAttribute("notes", searchedNotes);
            model.addAttribute("searchValue", search);
        } else {
            model.addAttribute("notes", noteService.listAll());
        }

        return TEMPLATE_NOTES_LIST;
    }

    @GetMapping("/create")
    public String createNote(final Model model) {
       model.addAttribute("note", new Note());
       return TEMPLATE_CREATE_NOTE;
    }

    @PostMapping("/create")
    public String creatingNote(final @ModelAttribute("note") Note note) {
        noteService.saveAndUpdate(note);
        return BASE_REDIRECT;
    }

    @GetMapping("/edit")
    public String editNoteById(final @RequestParam("id") Long id,
                               final Model model) {
        final Note note = noteService.findById(id);
        model.addAttribute("note", note);
        return TEMPLATE_EDIT_NOTE;
    }

    @PostMapping("/edit")
    public String saveEditingNoteById(final @ModelAttribute("note") Note note) {
        creatingNote(note);
        return BASE_REDIRECT;
    }

    @GetMapping("/delete")
    public String deleteNoteById(final @RequestParam("id") Long id) {
        noteService.deleteById(id);
        return BASE_REDIRECT;
    }
}
