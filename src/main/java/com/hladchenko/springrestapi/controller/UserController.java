package com.hladchenko.springrestapi.controller;

import com.hladchenko.springrestapi.entity.Note;
import com.hladchenko.springrestapi.entity.User;
import com.hladchenko.springrestapi.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public EntityModel<User> createUser(@RequestBody User user) {
        return EntityModel.of(userService.addUser(user),
                linkTo(methodOn(UserController.class)
                        .getUser(user.getId()))
                        .withSelfRel());
    }

    @DeleteMapping
    public EntityModel<User> deleteUser(@RequestParam UUID uuid) {
        return EntityModel.of(userService.deleteUser(uuid),
                linkTo(methodOn(UserController.class)
                        .getUsers())
                        .withRel("Get all users"));
    }

    @GetMapping("/{uuid}")
    public EntityModel<User> getUser(@PathVariable UUID uuid) {
        return EntityModel.of(userService.getUser(uuid),
                linkTo(methodOn(UserController.class)
                        .deleteUser(uuid))
                        .withRel("Delete URL"),
                linkTo(methodOn(NoteController.class)
                        .getNotes(uuid))
                        .withRel("Get all user notes"));
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/{uuid}/note")
    public User addNote(@PathVariable UUID uuid, @RequestBody Note note) throws Exception {
        return userService.addNote(uuid, note);
    }
}
