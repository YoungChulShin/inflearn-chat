package study.spring.chatserver.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.chatserver.chat.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
