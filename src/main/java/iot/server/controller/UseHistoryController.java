package iot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import iot.server.model.requestDto.userhistory.UserHistorySaveDto;
import iot.server.model.response.CommonResult;
import iot.server.service.ResponseService;
import iot.server.service.UserHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4. 사용이력"})
@RestController
@RequiredArgsConstructor
public class UseHistoryController {

    private final UserHistoryService userHistoryService;
    private final ResponseService responseService;

    @ApiOperation(value = "사용 이력 등록",notes = "소독기 사용이력을 등록한다.")
    @PostMapping("/useHistory")
    public CommonResult saveUserHistory(@RequestBody UserHistorySaveDto userHistorySaveDto){
        userHistoryService.saveUserHistory(userHistorySaveDto);
        return responseService.getSuccessResult();
    }



}
