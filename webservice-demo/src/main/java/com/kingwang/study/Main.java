package com.kingwang.study;

import com.kingwang.study.service.impl.TestServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/testws", new TestServiceImpl());

        System.out.println("WebService STARTED!!!");
    }
}