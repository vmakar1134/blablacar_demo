package com.makar.blablacar.service;

import com.makar.blablacar.domain.User;
import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.domain.response.UserResponse;

public interface UserService {

    UserResponse save(UserRequest request);

    User get(Long id);

}
