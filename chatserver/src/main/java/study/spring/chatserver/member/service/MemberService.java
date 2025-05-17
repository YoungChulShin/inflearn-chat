package study.spring.chatserver.member.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.chatserver.member.domain.Member;
import study.spring.chatserver.member.dto.MemberListResDto;
import study.spring.chatserver.member.dto.MemberLoginReqDto;
import study.spring.chatserver.member.dto.MemberSaveReqDto;
import study.spring.chatserver.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public Member create(MemberSaveReqDto memberSaveReqDto) {
    if (memberRepository.existsByEmail(memberSaveReqDto.getEmail())) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다");
    }

    Member newMember = Member.builder()
        .name(memberSaveReqDto.getName())
        .email(memberSaveReqDto.getEmail())
        .password(passwordEncoder.encode(memberSaveReqDto.getPassword()))
        .build();

    return memberRepository.save(newMember);
  }

  public Member login(MemberLoginReqDto memberLoginReqDto) {
    Member member = memberRepository.findByEmail(memberLoginReqDto.getEmail())
        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일 입니다"));
    if (!passwordEncoder.matches(memberLoginReqDto.getPassword(), member.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
    }

    return member;
  }

  @Transactional(readOnly = true)
  public List<MemberListResDto> findAll() {
    List<Member> members = memberRepository.findAll();
    List<MemberListResDto> memberListResDtos = new ArrayList<>();
    for (Member member : members) {
      MemberListResDto memberListResDto = new MemberListResDto();
      memberListResDto.setId(member.getId());
      memberListResDto.setEmail(member.getEmail());
      memberListResDto.setName(member.getName());
      memberListResDtos.add(memberListResDto);
    }

    return memberListResDtos;
  }

}
