package com.makar.blablacar.controller;

import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.domain.response.UserResponse;
import com.makar.blablacar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        UserResponse response = userService.save(request);
        return new ResponseEntity<>(response, CREATED);
    }
}
