package com.kingwang.study.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Course {
    private String id;

    private String name;

    private Set<Student> students;
}
