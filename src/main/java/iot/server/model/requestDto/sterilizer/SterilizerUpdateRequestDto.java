package iot.server.model.requestDto.sterilizer;

import iot.server.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SterilizerUpdateRequestDto {

    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String code;
    @NotEmpty
    private String position;

}
