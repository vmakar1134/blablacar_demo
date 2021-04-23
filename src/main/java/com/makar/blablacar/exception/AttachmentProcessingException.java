package com.makar.blablacar.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentProcessingException extends RuntimeException {

    private String filename;

    public AttachmentProcessingException(Throwable cause) {
        super(cause);
    }

    public AttachmentProcessingException(String filename, Throwable cause) {
        super(cause);
        this.filename = filename;
    }

}
