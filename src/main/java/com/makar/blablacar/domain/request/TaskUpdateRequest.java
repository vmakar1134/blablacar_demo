package com.makar.blablacar.domain.request;

import com.makar.blablacar.domain.TaskStatus;
import lombok.Data;

@Data
public class TaskUpdateRequest {

    private TaskStatus status;

    private Long assigneeId;

    private String description;

    private String commentText;
}
