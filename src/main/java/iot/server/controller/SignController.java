package iot.server.controller;

import iot.server.advice.exception.EmailSigninFailedException;
import iot.server.config.security.JwtTokenProvider;
import iot.server.domain.Member;
import iot.server.model.requestDto.member.MemberSigninDto;
import iot.server.model.requestDto.member.MemberSignupDto;
import iot.server.model.response.CommonResult;
import iot.server.model.response.SingleResult;
import iot.server.repository.MemberRepository;
import iot.server.service.MemberService;
import iot.server.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class SignController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public CommonResult signup(@RequestBody @Valid MemberSignupDto memberSignupDto){
        log.info("입력");
        memberService.saveMember(memberSignupDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/signin")
    public SingleResult<String> signin(@RequestBody @Valid MemberSigninDto memberSigninDto){
        Member member = memberRepository.findByEmail(memberSigninDto.getEmail()).orElseThrow(EmailSigninFailedException::new);
        if(!passwordEncoder.matches(memberSigninDto.getPassword(),member.getPassword())){
            throw new EmailSigninFailedException();
        }

        return responseService.getSingleResult(
                jwtTokenProvider.createToken(String.valueOf(member.getId()),member.getRoles()));
    }
}

