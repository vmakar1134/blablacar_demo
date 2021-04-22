package com.makar.blablacar.controller;

import com.makar.blablacar.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final TaskService taskService;

    public UserController(TaskService taskService) {
        this.taskService = taskService;
    }

}
