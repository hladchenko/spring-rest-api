package com.hladchenko.springrestapi.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String name;
    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        notes.add(note);
    }
}
