package com.kingwang.study.service;

import com.kingwang.study.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global notification");

        simpMessagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId) {
        ResponseMessage message = new ResponseMessage("Private notification");

        simpMessagingTemplate.convertAndSendToUser(userId, "/topic/private-notifications", message);
    }
}
