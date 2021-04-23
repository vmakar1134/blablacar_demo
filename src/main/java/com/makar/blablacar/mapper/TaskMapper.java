package com.makar.blablacar.mapper;


import com.makar.blablacar.domain.Task;
import com.makar.blablacar.domain.request.TaskRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @BeanMapping(resultType = Task.class)
    Task toEntity(TaskRequest taskRequest);

    @BeanMapping(resultType = TaskResponse.class)
    @Named(value = "toResponse")
    TaskResponse toResponse(Task task);

    @BeanMapping(resultType = TaskResponse.class)
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "attachments", source = "attachments")
    TaskResponse toDetailResponse(Task task);

    @IterableMapping(qualifiedByName = "toResponse")
    List<TaskResponse> toResponse(List<Task> tasks);
}
