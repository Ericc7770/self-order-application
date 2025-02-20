package com.example.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    public static final Integer ENABLED = 1;
    public static final Integer DISABLED = 0;

    private Integer id;
    private String name;
    private String userName;
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
