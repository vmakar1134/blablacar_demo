package com.makar.blablacar.domain;

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
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException ignored) {
        }

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException ignored) {
        }
        this.data = outputStream.toByteArray();
        return this;
    }

    public Attachment decompressData() {
        if (isNull(data)) {
            return this;
        }
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        this.data = outputStream.toByteArray();
        return this;
    }
}
