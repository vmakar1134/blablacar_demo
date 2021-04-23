package com.makar.blablacar.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    @OneToOne(mappedBy = "user", cascade = {MERGE, PERSIST})
    private Rate rate;

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();
}
