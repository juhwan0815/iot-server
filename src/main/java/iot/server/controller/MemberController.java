package iot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = {"2. Member"})
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @ApiOperation(value = "모든 회원 조회",notes = "모든 회원을 조회한다")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
              ,dataType = "String", paramType = "header")
    })
    @GetMapping("/members")
    public ListResult<MemberDto> findMembers(){
        List<MemberDto> result = memberService.findAll();
        return responseService.getListResult(result);
    }

    @ApiOperation(value = "회원 한명 조회",notes = "회원 한명을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @GetMapping("/member/{id}")
    public SingleResult<MemberDto> findMember(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);

        MemberDto result = memberService.findOne(id);
        return responseService.getSingleResult(result);
    }

    @ApiOperation(value = "회원 수정",notes = "회원 수정을 한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header"),
    })
    @PutMapping("/member")
    public CommonResult updateMember(@RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        memberService.updateMember(loginEmail,memberUpdateRequestDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "회원 탈퇴",notes = "회원탈퇴를 한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @DeleteMapping("/member")
    public CommonResult deleteMember(@RequestBody @Valid MemberDeleteDto memberDeleteDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        memberService.deleteMember(loginEmail,memberDeleteDto);
        return responseService.getSuccessResult();
    }


}
