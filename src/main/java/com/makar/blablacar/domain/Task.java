package com.makar.blablacar.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.persistence.CascadeType.*;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdAt;

    private String title;

    private String description;

    private String authorName;

    @Enumerated
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User assignee;

    @OneToMany(mappedBy = "task", cascade = {PERSIST, MERGE})
    private List<Attachment> attachments = new ArrayList<>();

    public Task addAttachment(@NonNull Attachment attachment) {
        this.attachments.add(checkNotNull(attachment));
        return this;
    }

    public Task addComment(@NonNull Comment comment) {
        this.comments.add(checkNotNull(comment));
        return this;
    }
}
