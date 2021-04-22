package com.makar.blablacar.service.impl;

import com.makar.blablacar.domain.User;
import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.domain.response.UserResponse;
import com.makar.blablacar.exception.EntityNotFoundException;
import com.makar.blablacar.mapper.UserMapper;
import com.makar.blablacar.repository.UserRepository;
import com.makar.blablacar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper USER_MAPPER = UserMapper.INSTANCE;

    @Override
    public UserResponse save(UserRequest request) {
        User userToSave = USER_MAPPER.toEntity(request);
        User user = userRepository.save(userToSave);
        return USER_MAPPER.toResponse(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
}
