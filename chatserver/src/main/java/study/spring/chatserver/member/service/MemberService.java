package study.spring.chatserver.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.chatserver.member.domain.Member;
import study.spring.chatserver.member.dto.MemberSaveReqDto;
import study.spring.chatserver.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member create(MemberSaveReqDto memberSaveReqDto) {
    if (memberRepository.existsByEmail(memberSaveReqDto.getEmail())) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다");
    }

    Member newMember = Member.builder()
        .name(memberSaveReqDto.getName())
        .email(memberSaveReqDto.getEmail())
        .password(memberSaveReqDto.getPassword())
        .build();

    return memberRepository.save(newMember);
  }

}
