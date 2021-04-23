package com.makar.blablacar.domain;

import com.makar.blablacar.exception.AttachmentProcessingException;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static java.util.Objects.isNull;

@Entity
@Data
@Getter
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Attachment compressData(MultipartFile file) {
        if (isNull(file)) {
            return this;
        }
        Deflater deflater = getDeflater(getBytes(file));
        ByteArrayOutputStream outputStream = processDeflater(deflater);
        this.data = outputStream.toByteArray();
        return this;
    }

    public Attachment decompressData() {
        if (isNull(this.data)) {
            return this;
        }
        ByteArrayOutputStream outputStream = processInflater(getInflater());
        this.data = outputStream.toByteArray();
        return this;
    }

    private ByteArrayOutputStream processInflater(Inflater inflater) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
        } catch (DataFormatException ex) {
            throw new AttachmentProcessingException(ex);
        }
        closeStream(outputStream);
        return outputStream;
    }

    private ByteArrayOutputStream processDeflater(Deflater deflater) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        closeStream(outputStream);
        return outputStream;
    }

    private void closeStream(ByteArrayOutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException ex) {
            throw new AttachmentProcessingException(ex);
        }
    }

    private Inflater getInflater() {
        Inflater inflater = new Inflater();
        inflater.setInput(this.data);
        return inflater;
    }

    private Deflater getDeflater(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        return deflater;
    }

    private byte[] getBytes(MultipartFile file) {
        try {
            this.data = file.getBytes();
        } catch (IOException ex) {
            throw new AttachmentProcessingException(file.getOriginalFilename(), ex);
        }
        return this.data;
    }

}
