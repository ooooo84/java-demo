package com.kingwang.study.entity;

import java.util.Objects;
import java.util.Set;

/**
 * Hibernate和lombok同时使用时，若使用lombok自动生成的hashCode、toString等方法会导致StackOverflow，
 * 原因是这些方法之间互相调用造成死循环
 */
public class Customer {
    private String id;

    private String name;

    private Set<CustomerOrder> orders;

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
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
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
