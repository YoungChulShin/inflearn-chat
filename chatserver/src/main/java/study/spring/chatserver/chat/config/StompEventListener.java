package study.spring.chatserver.chat.config;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

// 스프링과 stomp는 기본적으로 세션 관리를 자동으로 처리한다.
// 연결/해제 이벤트를 기록, 연결된 세션 수를 실시간으로 확인한다.
@Component
public class StompEventListener {

  private final Set<String> sessions = ConcurrentHashMap.newKeySet();

  @EventListener
  public void connectHandle(SessionConnectEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    sessions.add(accessor.getSessionId());
    System.out.println("connect session ID: " + accessor.getSessionId());
    System.out.println("total session: " + sessions.size());
  }

  @EventListener
  public void disconnectHandle(SessionDisconnectEvent event) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    sessions.remove(accessor.getSessionId());
    System.out.println("disconnect session ID: " + accessor.getSessionId());
    System.out.println("total session: " + sessions.size());
  }
}
