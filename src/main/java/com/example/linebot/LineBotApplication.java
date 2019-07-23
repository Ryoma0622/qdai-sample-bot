package com.example.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.RequiredArgsConstructor;

@LineMessageHandler
@SpringBootApplication
@RequiredArgsConstructor
public class LineBotApplication {

//    private final LineMessagingClient client;

    public static void main(String[] args) {
        SpringApplication.run(LineBotApplication.class, args);
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        return TextMessage.builder().text(event.getMessage().getText()).build();
    }

//    @EventMapping
//    public void handleTextMessageEventUsingClient(MessageEvent<TextMessageContent> event) {
//        client.replyMessage(new ReplyMessage(event.getReplyToken(), TextMessage.builder().text(event.getMessage().getText()).build()));
//    }

}
