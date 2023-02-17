package com.kingwang.study.service;

import com.kingwang.study.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 使用API发送WebSocket消息
     * 使用 SimpMessagingTemplate 类实例上的 convertAndSend(destination, payload) 实现消息回复
     *
     * @param message
     */
    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/messages", response);
    }

    /**
     * 使用API给特定用户发送消息
     *
     * @param userId
     * @param message
     */
    public void notifyUser(final String userId, final String message) {
        ResponseMessage response = new ResponseMessage(message);

        simpMessagingTemplate.convertAndSendToUser(userId, "/topic/private-messages", response);
    }
}
