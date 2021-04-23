package com.makar.blablacar.service.impl;

import com.makar.blablacar.domain.Comment;
import com.makar.blablacar.domain.Task;
import com.makar.blablacar.domain.request.CommentRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import com.makar.blablacar.mapper.TaskMapper;
import com.makar.blablacar.repository.CommentRepository;
import com.makar.blablacar.service.CommentService;
import com.makar.blablacar.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final TaskService taskService;

    private final TaskMapper TASK_MAPPER = TaskMapper.INSTANCE;

    @Override
    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public TaskResponse addComment(Long taskId, CommentRequest commentRequest) {
        Task task = taskService.getById(taskId);
        Comment comment = commentRepository.save(new Comment(commentRequest.getText()));
        task.addComment(comment);
        return TASK_MAPPER.toResponse(task);
    }
}
