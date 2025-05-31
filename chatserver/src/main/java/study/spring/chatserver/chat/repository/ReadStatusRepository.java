package study.spring.chatserver.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.chatserver.chat.domain.ReadStatus;

public interface ReadStatusRepository extends JpaRepository<ReadStatus, Long> {

}
