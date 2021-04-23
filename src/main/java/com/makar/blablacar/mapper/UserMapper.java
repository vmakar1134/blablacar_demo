package com.makar.blablacar.mapper;


import com.makar.blablacar.domain.User;
import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.domain.response.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @BeanMapping(resultType = User.class)
    User toEntity(UserRequest userRequest);

    @BeanMapping(resultType = UserResponse.class)
    UserResponse toResponse(User task);

    List<UserResponse> toResponse(List<User> tasks);
}
