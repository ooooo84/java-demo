package com.kingwang.study.controller;

import com.kingwang.study.dto.Message;
import com.kingwang.study.service.WebSocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebServiceController {
    @Resource
    private WebSocketService webSocketService;

    /**
     * 使用API调用WebSocket
     *
     * @param message
     */
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        webSocketService.notifyFrontend(message.getMessageContent());
    }
}
