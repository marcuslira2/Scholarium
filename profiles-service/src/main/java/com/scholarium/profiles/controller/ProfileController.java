package com.scholarium.profiles.controller;

import com.scholarium.profiles.dto.CreateProfileRecord;
import com.scholarium.profiles.dto.ProfileRecord;
import com.scholarium.profiles.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService userService;

    public ProfileController(ProfileService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProfileRecord user) throws IllegalAccessException {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileRecord> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User deleted!");
    }

}
