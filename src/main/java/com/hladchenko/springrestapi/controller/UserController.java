package com.hladchenko.springrestapi.controller;

import com.hladchenko.springrestapi.entity.Note;
import com.hladchenko.springrestapi.entity.User;
import com.hladchenko.springrestapi.excetion.UserNotFoundException;
import com.hladchenko.springrestapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping
    public User deleteUser(@RequestParam UUID uuid) {
        return userService.deleteUser(uuid);
    }

    @GetMapping
    public User getUser(@RequestParam UUID uuid) {
        return userService.getUser(uuid);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/{uuid}/note")
    public User addNote(@PathVariable UUID uuid, @RequestBody Note note) throws Exception {
        return userService.addNote(uuid, note);
    }
}
