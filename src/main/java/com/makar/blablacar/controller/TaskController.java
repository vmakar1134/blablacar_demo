package com.makar.blablacar.controller;

import com.makar.blablacar.domain.FilterCriteria;
import com.makar.blablacar.domain.request.TaskRequest;
import com.makar.blablacar.domain.request.TaskUpdateRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import com.makar.blablacar.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping("")
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest taskRequest) {
        TaskResponse response = taskService.create(taskRequest);
        return new ResponseEntity<>(response, CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id,
                                               @RequestBody(required = false) TaskUpdateRequest request,
                                               @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        TaskResponse response = taskService.update(id, request, attachment);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(defaultValue = "id") String sort,
                                                       @RequestParam String fieldName,
                                                       @RequestParam String fieldValue) {
        FilterCriteria filterCriteria = new FilterCriteria(fieldName, fieldValue);
        List<TaskResponse> response = taskService.getAll(sort, filterCriteria);
        return new ResponseEntity<>(response, FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long id) {
        TaskResponse response = taskService.get(id);
        return new ResponseEntity<>(response, FOUND);
    }


    @DeleteMapping("deleteComment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) {
        taskService.removeComment(commentId);
        return new ResponseEntity<>(ACCEPTED);
    }
}
