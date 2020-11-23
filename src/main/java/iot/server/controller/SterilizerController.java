package iot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import iot.server.domain.Sterilizer;
import iot.server.model.requestDto.sterilizer.SterilizerSaveRequestDto;
import iot.server.model.requestDto.sterilizer.SterilizerUpdateRequestDto;
import iot.server.model.response.CommonResult;
import iot.server.model.response.ListResult;
import iot.server.model.response.SingleResult;
import iot.server.model.responseDto.sterilizer.SterilizerDetailDto;
import iot.server.model.responseDto.sterilizer.SterilizerDto;
import iot.server.service.ResponseService;
import iot.server.service.SterilizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"3. Sterilizer"})
@RestController
@RequiredArgsConstructor
public class SterilizerController {

    private final SterilizerService sterilizerService;
    private final ResponseService responseService;

    @ApiOperation(value = "소독기 등록",notes = "소독기 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @PostMapping("/sterilizer")
    public CommonResult saveSterilizer(@RequestBody @Valid SterilizerSaveRequestDto sterilizerSaveRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        sterilizerService.saveSterilizer(loginEmail,sterilizerSaveRequestDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "자신의 소독기리스트 조회",notes = "현재 접속한 사람의 이메를 조회하여 소독기를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @GetMapping("/sterilizers")
    public ListResult<SterilizerDto> findSterilizers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        List<SterilizerDto> sterilizers = sterilizerService.findSterilizers(loginEmail);
        return responseService.getListResult(sterilizers);
    }

    @ApiOperation(value = "소독기 1개 자세하게 조회",notes = "소독기를 자세하게 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @GetMapping("/sterilizer/{sterilizerId}")
    public SingleResult<SterilizerDetailDto> findDetailSterilizer(@PathVariable Long sterilizerId){
        SterilizerDetailDto sterilizerDetailDto = sterilizerService.findSterilizer(sterilizerId);
        return responseService.getSingleResult(sterilizerDetailDto);
    }

    @ApiOperation(value = "소독기의 주소 정보를 변경",notes = "소독기의 주소 정보 변경을 한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @PutMapping("/sterilizer/{sterilizerId}")
    public CommonResult updateSterilizer(@PathVariable Long sterilizerId
            , @RequestBody @Valid SterilizerUpdateRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        sterilizerService.updateSterilizer(sterilizerId,requestDto,loginEmail);
        return responseService.getSuccessResult();
    }


    @ApiOperation(value = "자신의 소독기중 한개 삭제",notes = "자신의 소독기를 삭제한다")
    @DeleteMapping("/sterilizer/{sterilizerId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    public CommonResult deleteSterilizer(@PathVariable Long sterilizerId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginEmail = authentication.getName();
        sterilizerService.deleteSterilizer(sterilizerId,loginEmail);
        return responseService.getSuccessResult();
    }



}
