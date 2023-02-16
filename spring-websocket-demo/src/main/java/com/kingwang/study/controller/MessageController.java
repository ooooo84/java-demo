package com.kingwang.study.controller;

import com.kingwang.study.dto.Message;
import com.kingwang.study.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
    /**
     * 使用Controller中的方法处理WebSocket消息
     * 使用MessageMapping注解指定需要接收消息的URL，
     * 注意此URL还需要加上在 WebSocketConfig 中指定的 ApplicationDestinationPrefixes
     * <p>
     * 使用SendTo注解指定回复的消息发送到的Destination
     *
     * @param message
     * @return
     * @throws InterruptedException
     */
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage handleMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);

        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
