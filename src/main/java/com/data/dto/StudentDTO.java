package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String name;
    private int age;
    private String classStd;
    private String email;
    private String address;
    private String phone;
}
