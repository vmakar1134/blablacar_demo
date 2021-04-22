package com.makar.blablacar.service;


import com.makar.blablacar.domain.request.UserRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String updateUser(UserRequest request, MultipartFile attachment);

}
