package study.spring.chatserver.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebsocketConfig implements WebSocketMessageBrokerConfigurer {

  private final StompHandler stompHandler;

  public StompWebsocketConfig(StompHandler stompHandler) {
    this.stompHandler = stompHandler;
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/connect")
        .setAllowedOrigins("http://localhost:3000")
        .withSockJS();  // ws://가 아니라 http:// 엔드포인트를 사용할 수 있게 해주는 sockJs 라이브러리를 통한 요청을 허용한다.
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // publish/1 형태로 메시지 발행해야 한다.
    // publish로 시작하는 url 패턴으로 메시지가 발행되면 @Controller 객체의 @MessageMapping 메서드로 라우팅
    registry.setApplicationDestinationPrefixes("/publish");

    // topic/1 형태로 메시지를 수힌해야 한다.
    registry.enableSimpleBroker("/topic");
    // 내장 브로커를 사용하지 않으려면 'registry.enableStompBrokerRelay()' 를 사용한다.
  }

  // 웹소켓 요청(connect, subscribe, disconnect) 등의 요청시에는 http header 등 http 메시지를 넣을 수 있고,
  // 이를 interceptor를 통해서 가로채 토큰을 검증할 수 있다.
  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(stompHandler);
  }
}
