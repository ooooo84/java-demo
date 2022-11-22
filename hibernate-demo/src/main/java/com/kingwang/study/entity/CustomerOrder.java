package com.kingwang.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Hibernate和lombok同时使用时，若使用lombok自动生成的hashCode、toString等方法会导致StackOverflow，
 * 原因是这些方法之间互相调用造成死循环
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomerOrder {
    private String id;

    private String name;

    private Customer customer;

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerOrder order = (CustomerOrder) o;
        return Objects.equals(id, order.id) && Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
