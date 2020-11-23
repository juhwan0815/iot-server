package iot.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import iot.server.model.requestDto.disinfectant.DisinfectantSaveDto;
import iot.server.model.response.CommonResult;
import iot.server.service.ChangeHistoryService;
import iot.server.service.DisinfectantService;
import iot.server.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"5. 소독기"})
@RestController
@RequiredArgsConstructor
public class DisinfectantController {

    private final DisinfectantService disinfectantService;
    private final ResponseService responseService;
    private final ChangeHistoryService changeHistoryService;

    @ApiOperation(value = "소독제 등록",notes = "소독기의 소독제를 제거하고 다시 저장")
    @PostMapping("/disinfectant")
    public CommonResult saveDisinfectant(@RequestBody @Valid DisinfectantSaveDto disinfectantSaveDto){
        disinfectantService.saveDisinfectant(disinfectantSaveDto);
        changeHistoryService.saveChangeHistory(disinfectantSaveDto);
        return responseService.getSuccessResult();
    }
}
