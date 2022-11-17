package com.kingwang.study.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataObj implements Serializable {
    private static final long serialVersionUID = 6435134359836399326L;

    private String name;

    private Integer age;
}
