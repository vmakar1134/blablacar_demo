package com.makar.blablacar.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @OneToOne
    private User user;

}
