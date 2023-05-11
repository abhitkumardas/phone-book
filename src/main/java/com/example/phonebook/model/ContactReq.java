package com.example.phonebook.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
public class ContactReq implements Serializable {
    private String firstName;
    private String lastName;
    private String mobile;
}
