package com.makar.blablacar.domain.response;


import com.makar.blablacar.domain.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskResponse {

    private Long id;

    private LocalDate createdAt;

    private String title;

    private String description;

    private String authorName;

    private TaskStatus status;

    private UserResponse assignee;

    private List<CommentResponse> comments = new ArrayList<>();
}
