package com.makar.blablacar.mapper;


import com.makar.blablacar.domain.Task;
import com.makar.blablacar.domain.request.TaskRequest;
import com.makar.blablacar.domain.response.TaskResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @BeanMapping(resultType = Task.class)
    Task toEntity(TaskRequest taskRequest);

    @BeanMapping(resultType = TaskResponse.class)
    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponse(List<Task> tasks);
}
