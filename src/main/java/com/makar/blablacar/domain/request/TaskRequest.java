package com.makar.blablacar.domain.request;

import com.makar.blablacar.domain.TaskStatus;
import lombok.Data;

@Data
public class TaskRequest {

    private String title;

    private String name;

    private String authorName;

    private Long assigneeId;

    private TaskStatus status;

}
