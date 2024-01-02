package com.distributed.systems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
public class Application {
    @Column(nullable = false)
    private String applicantsAFM;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String staffId;
    @Column(nullable = false)
    private Date date;
    @Id
    private String applicationId;
}
