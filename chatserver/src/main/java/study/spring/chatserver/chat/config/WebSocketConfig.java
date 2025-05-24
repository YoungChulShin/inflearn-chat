//package study.spring.chatserver.chat.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
//public class WebSocketConfig implements WebSocketConfigurer {
//
//  private final SimpleWebSocketHandler webSocketHandler;
//
//  @Override
//  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//    // /connect url websocket 연결이 들어오면 handler 클래스가 처리한다.
//    registry
//        .addHandler(webSocketHandler, "/connect")
//        .setAllowedOrigins("http://localhost:3000")
//    ;
//  }
//}
