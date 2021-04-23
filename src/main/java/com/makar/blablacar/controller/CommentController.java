package com.makar.blablacar.controller;

import com.makar.blablacar.domain.request.CommentRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import com.makar.blablacar.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/comment/{taskId}")
    public ResponseEntity<TaskResponse> addComment(@PathVariable Long taskId,
                                                   @RequestBody CommentRequest commentRequest) {
        TaskResponse response = commentService.addComment(taskId, commentRequest);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.removeComment(commentId);
        return new ResponseEntity<>(ACCEPTED);
    }
}
