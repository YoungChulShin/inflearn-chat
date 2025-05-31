package study.spring.chatserver.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.chatserver.chat.repository.ChatMessageRepository;
import study.spring.chatserver.chat.repository.ChatParticipantRepository;
import study.spring.chatserver.chat.repository.ChatRoomRepository;
import study.spring.chatserver.chat.repository.ReadStatusRepository;
import study.spring.chatserver.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

  private final ChatRoomRepository chatRoomRepository;
  private final ChatParticipantRepository chatParticipantRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final ReadStatusRepository readStatusRepository;
  private final MemberRepository memberRepository;

}
