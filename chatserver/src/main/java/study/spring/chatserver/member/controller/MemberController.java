package study.spring.chatserver.member.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.chatserver.common.auth.JwtTokenProvider;
import study.spring.chatserver.member.dto.MemberLoginReqDto;
import study.spring.chatserver.member.dto.MemberSaveReqDto;
import study.spring.chatserver.member.domain.Member;
import study.spring.chatserver.member.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/create")
  public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto) {
    Member member = memberService.create(memberSaveReqDto);
    return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
  }

  @PostMapping("/doLogin")
  public ResponseEntity<?> doLogin(@RequestBody MemberLoginReqDto memberLoginReqDto) {
    Member member = memberService.login(memberLoginReqDto);
    String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().name());

    Map<String, String> loginInfo = new HashMap<>();
    loginInfo.put("id", member.getId().toString());
    loginInfo.put("token", jwtToken);
    return new ResponseEntity<>(loginInfo, HttpStatus.OK);
  }

}
