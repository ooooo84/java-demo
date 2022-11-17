package com.kingwang.study.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsResult implements Serializable {
    private static final long serialVersionUID = 4900921724735139224L;

    private Boolean success;

    private DataObj data;
}
