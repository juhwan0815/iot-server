package iot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import iot.server.model.responseDto.changehistory.ChangeHistoryDto;
import iot.server.model.response.ListResult;
import iot.server.service.ChangeHistoryService;
import iot.server.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = {"6.교체이력"})
@RestController
@RequiredArgsConstructor
public class ChangeHistoryController{

    private final ResponseService responseService;
    private final ChangeHistoryService changeHistoryService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN",value = "로그인 성공 후 access_token",required = true
                    ,dataType = "String", paramType = "header")
    })
    @GetMapping("/changeHistory")
    public ListResult<ChangeHistoryDto> findChangeHistory(@ApiParam @RequestParam Long sterilizerId){
        List<ChangeHistoryDto> changeHistory = changeHistoryService.findChangeHistory(sterilizerId);
        return responseService.getListResult(changeHistory);
    }
    
}
