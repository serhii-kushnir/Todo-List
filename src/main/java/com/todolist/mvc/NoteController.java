package com.todolist.mvc;

import com.todolist.entity.Note;
import com.todolist.service.NoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
final class NoteController {
    private final NoteServiceImpl noteService;

    private static final String BASE_REDIRECT = "redirect:/note/list";

    @GetMapping("/list")
    public String getAllNotes(final Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "notes";
    }

    @GetMapping("/create")
    public String createNote(final Model model) {
       model.addAttribute("note", new Note());
        return "createNote";
    }

    @PostMapping("/create")
    public String saveCreatingNote(@ModelAttribute("note") Note note) {
        noteService.add(note);
        return BASE_REDIRECT;
    }

    @GetMapping("/edit")
    public String editNoteById(@RequestParam("id") Long id, final Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "editNote";
    }

    @PostMapping("/edit")
    public String saveEditingNoteById(@ModelAttribute("note") Note note) {
        noteService.update(note);
        return BASE_REDIRECT;
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam("id") Long id) {
        noteService.deleteById(id);
        return BASE_REDIRECT;
    }
}
