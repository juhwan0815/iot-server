package iot.server.controller;

import iot.server.model.requestDto.member.MemberDeleteDto;
import iot.server.model.requestDto.member.MemberUpdateRequestDto;
import iot.server.model.response.CommonResult;
import iot.server.model.response.ListResult;
import iot.server.model.response.SingleResult;
import iot.server.model.responseDto.member.MemberDto;
import iot.server.service.MemberService;
import iot.server.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping("/members")
    public ListResult<MemberDto> findMembers(){
        List<MemberDto> result = memberService.findAll();
        return responseService.getListResult(result);
    }

    @GetMapping("/member/{id}")
    public SingleResult<MemberDto> findMember(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);

        MemberDto result = memberService.findOne(id);
        return responseService.getSingleResult(result);
    }

    @PutMapping("/member")
    public CommonResult updateMember(@RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        memberService.updateMember(loginEmail,memberUpdateRequestDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/member")
    public CommonResult deleteMember(@RequestBody @Valid MemberDeleteDto memberDeleteDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        memberService.deleteMember(loginEmail,memberDeleteDto);
        return responseService.getSuccessResult();
    }


}
