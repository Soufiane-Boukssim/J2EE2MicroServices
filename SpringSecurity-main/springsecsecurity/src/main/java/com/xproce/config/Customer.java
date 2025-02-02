package com.xproce.config;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Customer {
    @Id
    private int id;
    private String email;
    private String pwd;
    private String role;
}
