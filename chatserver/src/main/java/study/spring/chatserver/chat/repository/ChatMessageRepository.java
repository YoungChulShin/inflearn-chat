package study.spring.chatserver.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.chatserver.chat.domain.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
