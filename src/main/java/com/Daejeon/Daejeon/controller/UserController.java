package com.Daejeon.controller;

import com.Daejeon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<String> getPassword(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(user.getPassword()))
                .orElse(ResponseEntity.notFound().build());
    }
}
