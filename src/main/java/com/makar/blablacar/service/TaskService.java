package com.makar.blablacar.service;


import com.makar.blablacar.domain.FilterCriteria;
import com.makar.blablacar.domain.request.TaskRequest;
import com.makar.blablacar.domain.request.TaskUpdateRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskService {

    TaskResponse update(Long taskId, TaskUpdateRequest request , MultipartFile attachment);

    TaskResponse create(TaskRequest request);

    List<TaskResponse> getAll(String sort, FilterCriteria filterCriteria);

    TaskResponse get(Long id);

    void removeComment(Long commentId);
}
