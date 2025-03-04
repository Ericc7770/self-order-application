package com.example.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Item implements Serializable {
    public final static Integer ENABLED = 1;
    public final static Integer DISABLED = 0;

    private Integer id;
    private String name;
    private Integer categoryId;
    private Double price;
    private String image;
    private String description;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer createEmployee;
    private Integer updateEmployee;
}
