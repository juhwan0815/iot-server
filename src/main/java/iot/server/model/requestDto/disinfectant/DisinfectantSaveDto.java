package iot.server.model.requestDto.disinfectant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DisinfectantSaveDto {

    @NotEmpty
    private String SerialNumber;

    @NotNull
    private Integer startWeight;

}
