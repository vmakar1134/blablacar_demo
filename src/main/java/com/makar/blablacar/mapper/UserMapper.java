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
    User userFrom(UserRequest userRequest);

    @BeanMapping(resultType = UserResponse.class)
    UserResponse userResponseFrom(User user);

    List<UserResponse> userResponseFrom(List<User> users);

}
