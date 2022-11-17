package com.kingwang.study.service;

import com.kingwang.study.dto.DataDTO;
import com.kingwang.study.model.WsResult;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface TestService {
    @WebMethod
    String sayHello(String msg);

    @WebMethod
    WsResult getData(DataDTO dto);
}
