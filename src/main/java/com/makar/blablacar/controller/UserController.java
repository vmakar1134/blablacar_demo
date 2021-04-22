package com.makar.blablacar.controller;

import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String updateUser( @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        UserRequest userRequest = new UserRequest();
        String s = userService.updateUser(userRequest, attachment);
        return s;
    }

}
