package com.makar.blablacar.service;

import com.makar.blablacar.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    Attachment save(MultipartFile attachment);

}
