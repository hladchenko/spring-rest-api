package com.hladchenko.springrestapi.service;

import com.hladchenko.springrestapi.entity.Note;
import com.hladchenko.springrestapi.entity.User;
import com.hladchenko.springrestapi.excetion.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private static final Map<UUID, User> userMap = new HashMap<>();

    public List<User> getUsers() {
        return userMap.values().stream().toList();
    }

    public User getUser(UUID uuid) {
        return userMap.get(uuid);
    }

    public User addUser(User user) {
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        userMap.put(uuid, user);
        return user;
    }

    public User deleteUser(UUID uuid) {
        return userMap.remove(uuid);
    }

    public User addNote(UUID uuid, Note note) throws Exception {
        User user = userMap.get(uuid);
        if (user == null) {
            throw new UserNotFoundException();
        }

        user.addNote(note);
        return user;
    }

    public List<Note> getNotes(UUID uuid) {
        return userMap.get(uuid).getNotes();
    }
}
