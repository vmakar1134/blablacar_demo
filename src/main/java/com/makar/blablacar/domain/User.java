package com.makar.blablacar.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();
}
