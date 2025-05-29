package study.spring.chatserver.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import study.spring.chatserver.chat.dto.ChatMessageReqDto;

@Controller
public class StompController {

  private final SimpMessageSendingOperations messageTemplate;

  public StompController(SimpMessageSendingOperations messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  // 방법1. MessageMapping과 SendTo를 한번에 이용
//  @MessageMapping("/{roomId}")  // 클라이언트에서 'publish/{roomId}'로 메시지를 전달하면
//  @SendTo("/topic/{roomId}")  // '/topic/{roomId}'로 메시지를 전달
//  public String sendMessage(@DestinationVariable Long roomId, String message) {
//    System.out.println(message);
//    return message;
//  }

  // 방법2. MessageMapping만 사용
  @MessageMapping("/{roomId}")
  public void sendMessage(@DestinationVariable Long roomId, ChatMessageReqDto chatMessageReqDto) {
    System.out.println(chatMessageReqDto.getMessage());
    messageTemplate.convertAndSend("/topic/" + roomId, chatMessageReqDto);
  }

}
