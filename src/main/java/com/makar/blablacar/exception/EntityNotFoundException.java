package com.makar.blablacar.exception;

public class EntityNotFoundException extends RuntimeException {

    private Long causeId;

    public EntityNotFoundException(Long id) {
        this.causeId = id;
    }

    public Long getCauseId() {
        return causeId;
    }
}
