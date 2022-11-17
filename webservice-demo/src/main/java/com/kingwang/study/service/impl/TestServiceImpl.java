package com.kingwang.study.service.impl;

import com.kingwang.study.dto.DataDTO;
import com.kingwang.study.model.DataObj;
import com.kingwang.study.model.WsResult;
import com.kingwang.study.service.TestService;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(endpointInterface = "com.kingwang.study.service.TestService")
@SOAPBinding
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String msg) {
        return "Hello " + msg;
    }

    @Override
    public WsResult getData(DataDTO dto) {
        DataObj data = new DataObj();
        data.setAge(30);
        data.setName("Kun");

        WsResult result = new WsResult();
        result.setSuccess(true);
        result.setData(data);

        return result;
    }
}
