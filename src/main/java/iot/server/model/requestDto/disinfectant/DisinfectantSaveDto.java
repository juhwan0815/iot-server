package iot.server.model.requestDto.disinfectant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DisinfectantSaveDto {

    @NotBlank(message = "시리얼번호는 필수입니다.")
    private String SerialNumber;

    @NotNull(message = "소독제 시작 무게는 필수입니다.")
    @Min(value = 100,message = "소독제 시작 무게는 100이상")
    private Integer startWeight;

}
