package study.spring.chatserver.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.chatserver.chat.domain.ChatParticipant;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {

}
