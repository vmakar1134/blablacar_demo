package com.makar.blablacar.service;

import com.makar.blablacar.domain.request.CommentRequest;
import com.makar.blablacar.domain.response.TaskResponse;

public interface CommentService {

    void removeComment(Long commentId);

    TaskResponse addComment(Long taskId, CommentRequest commentRequest);

}
