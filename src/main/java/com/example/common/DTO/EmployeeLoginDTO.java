package com.example.common.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeLoginDTO implements Serializable {
    private String username;
    private String password;
}
