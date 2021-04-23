package com.makar.blablacar.service.impl;

import com.makar.blablacar.domain.*;
import com.makar.blablacar.domain.request.TaskRequest;
import com.makar.blablacar.domain.request.TaskUpdateRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import com.makar.blablacar.exception.EntityNotFoundException;
import com.makar.blablacar.mapper.TaskMapper;
import com.makar.blablacar.repository.CommentRepository;
import com.makar.blablacar.repository.TaskRepository;
import com.makar.blablacar.service.TaskService;
import com.makar.blablacar.service.UserService;
import com.makar.blablacar.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final UserService userService;

    private final TaskRepository taskRepository;

    private final CommentRepository commentRepository;

    private final TaskMapper TASK_MAPPER = TaskMapper.INSTANCE;

    @Override
    @Transactional
    public TaskResponse update(Long taskId, TaskUpdateRequest request, MultipartFile attachmentFile) {
        Task task = getById(taskId);
        updateTask(task, request);
        if (!attachmentFile.isEmpty()) {
            Attachment attachment = new Attachment();
            attachment.compressData(attachmentFile);
            attachment.setName(attachmentFile.getOriginalFilename());
            task.addAttachment(attachment);
        }
        return TASK_MAPPER.toResponse(task);
    }

    private void updateTask(Task task, TaskUpdateRequest request) {
        User user = userService.get(request.getAssigneeId());
        task.setAssignee(user);
        task.setStatus(request.getStatus());
        if (Objects.nonNull(request.getCommentText())) {
            task.addComment(new Comment(request.getCommentText()));
        }
    }

    @Override
    @Transactional
    public TaskResponse create(TaskRequest request) {
        Task task = TASK_MAPPER.toEntity(request);
        Task savedTask = taskRepository.save(task);
        return TASK_MAPPER.toResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAll(String sort, FilterCriteria filterCriteria) {
        Sort.Direction direction = getSortDirection(sort);
        String property = getSortProperty(sort);
        TaskSpecification taskSpecification = new TaskSpecification(filterCriteria);
        List<Task> tasks = taskRepository.findAllByAssigneeId(taskSpecification, Sort.by(direction, property));
        tasks.forEach(t -> t.setAttachments(getDecompressedAttachments(t)));
        return TASK_MAPPER.toResponse(tasks);
    }

    @Override
    public TaskResponse get(Long id) {
        Task task = taskRepository.findByIdFetch(id).orElseThrow(() -> new EntityNotFoundException(id));
        List<Attachment> attachments = getDecompressedAttachments(task);
        task.setAttachments(attachments);
        return TASK_MAPPER.toResponse(task);
    }

    private List<Attachment> getDecompressedAttachments(Task task) {
        return task.getAttachments()
                .stream()
                .map(Attachment::decompressData)
                .collect(Collectors.toList());
    }

    @Override
    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private Sort.Direction getSortDirection(String string) {
        final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;
        if (StringUtils.isBlank(string)) {
            return DEFAULT_DIRECTION;
        }
        String[] strings = string.split(",");
        if (strings.length < 2) {
            return DEFAULT_DIRECTION;
        }
        return Sort.Direction.valueOf(strings[1]);
    }

    private String getSortProperty(String string) {
        final String DEFAULT_FIELD = "createdAt";
        if (StringUtils.isBlank(string)) {
            return DEFAULT_FIELD;
        }
        String[] strings = string.split(",");
        return strings[0];
    }

    private Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }


}
