package com.makar.blablacar.service.impl;

import com.makar.blablacar.domain.Attachment;
import com.makar.blablacar.domain.User;
import com.makar.blablacar.domain.request.UserRequest;
import com.makar.blablacar.exception.EntityNotFoundException;
import com.makar.blablacar.repository.UserRepository;
import com.makar.blablacar.service.AttachmentService;
import com.makar.blablacar.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AttachmentService attachmentService;

    public UserServiceImpl(UserRepository userRepository, AttachmentService attachmentService) {
        this.userRepository = userRepository;
        this.attachmentService = attachmentService;
    }

    @Override
    public String updateUser(UserRequest request, MultipartFile attachmentFile) {
        Attachment save = attachmentService.save(attachmentFile);
        return save.getId().toString();
    }

    private User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }


}
