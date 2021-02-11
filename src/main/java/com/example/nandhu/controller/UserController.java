package com.example.nandhu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nandhu.model.User;
import com.example.nandhu.repository.UserRepository;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> GetUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User GetUser(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    @PostMapping("/")
    public User PostUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PutMapping("/user")
    public User PutUser(@RequestBody User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }
    @DeleteMapping("/{id}")
    public Integer DeleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return id;
    }
}