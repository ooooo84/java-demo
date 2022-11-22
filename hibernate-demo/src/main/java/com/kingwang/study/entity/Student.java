package com.kingwang.study.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Student {
    private String id;

    private String name;

    private Set<Course> courses;
}
