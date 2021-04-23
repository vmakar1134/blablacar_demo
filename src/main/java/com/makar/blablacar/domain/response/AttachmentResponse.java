package com.makar.blablacar.domain.response;

import lombok.Data;

@Data
public class AttachmentResponse {

    private Long id;

    private String name;

    private byte[] data;

}
