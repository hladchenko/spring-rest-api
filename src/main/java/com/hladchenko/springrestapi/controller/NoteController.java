package com.hladchenko.springrestapi.controller;

import com.hladchenko.springrestapi.entity.Note;
import com.hladchenko.springrestapi.entity.User;
import com.hladchenko.springrestapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/notes")
@RestController
public class NoteController {

    private final UserService userService;

    public NoteController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{uuid}")
    public User addNote(@PathVariable UUID uuid, @RequestBody Note note) throws Exception {
        return userService.addNote(uuid, note);
    }

    @GetMapping("/{uuid}")
    public List<Note> getNotes(@PathVariable UUID uuid) {
        return userService.getNotes(uuid);
    }
}
