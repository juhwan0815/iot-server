package iot.server.controller;

import io.swagger.annotations.*;
import iot.server.model.requestDto.usehistory.UseHistorySaveDto;
import iot.server.model.response.CommonResult;
import iot.server.model.response.ListResult;
import iot.server.model.responseDto.userHistory.UseHistoryByDateDto;
import iot.server.service.ResponseService;
import iot.server.service.UserHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"4. 사용이력"})
@RestController
@RequiredArgsConstructor
public class UseHistoryController {

    private final UserHistoryService userHistoryService;
    private final ResponseService responseService;

    @ApiOperation(value = "사용 이력 등록",notes = "소독기 사용이력을 등록한다.")
    @PostMapping("/useHistory")
    public CommonResult saveUserHistory(@RequestBody UseHistorySaveDto useHistorySaveDto){
        userHistoryService.saveUserHistory(useHistorySaveDto);
        return responseService.getSuccessResult();
    }


    @ApiOperation(value = "소독기의 날짜 사용이력 조회",notes = "소독기의 날짜별 사용이력 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @GetMapping("/useHistory")
    public ListResult<UseHistoryByDateDto> findUseHistoryByDate(@ApiParam(value = "소독기 Id",required = true) @RequestParam Long sterilizerId){
        List<UseHistoryByDateDto> result = userHistoryService.findUseHistoryByDate(sterilizerId);
        return responseService.getListResult(result);
    }
}
