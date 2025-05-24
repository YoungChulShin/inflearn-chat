//package study.spring.chatserver.chat.config;
//
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Component
//public class SimpleWebSocketHandler extends TextWebSocketHandler {
//
//  // 연결된 세션 관리: 스레드 세이프
//  private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
//
//  @Override
//  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    sessions.add(session);
//    System.out.println("Connected: " + session.getId());
//  }
//
//  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//    String payload = message.getPayload();
//    System.out.println("Received: " + payload);
//    for (WebSocketSession s : sessions) {
//      if (s.isOpen()) {
//        s.sendMessage(new TextMessage(payload));
//      }
//    }
//  }
//
//  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    sessions.remove(session);
//    System.out.println("Disconnected: " + session.getId());
//  }
//}
