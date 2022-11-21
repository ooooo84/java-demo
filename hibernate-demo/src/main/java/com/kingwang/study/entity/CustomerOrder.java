package com.kingwang.study.entity;

import lombok.Data;

@Data
public class CustomerOrder {
    private String id;

    private String name;

    private Customer customer;
}
