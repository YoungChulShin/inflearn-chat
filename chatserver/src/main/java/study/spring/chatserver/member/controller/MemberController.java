package study.spring.chatserver.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.chatserver.member.dto.MemberSaveReqDto;
import study.spring.chatserver.member.domain.Member;
import study.spring.chatserver.member.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/create")
  public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto) {
    Member member = memberService.create(memberSaveReqDto);
    return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);

  }

}
