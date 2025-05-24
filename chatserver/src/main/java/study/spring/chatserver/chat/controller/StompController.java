package study.spring.chatserver.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

  @MessageMapping("/{roomId}")  // 클라이언트에서 'publish/{roomId}'로 메시지를 전달하면
  @SendTo("/topic/{roomId}")  // '/topic/{roomId}'로 메시지를 전달
  public String sendMessage(@DestinationVariable Long roomId, String message) {
    System.out.println(message);
    return message;
  }
}
