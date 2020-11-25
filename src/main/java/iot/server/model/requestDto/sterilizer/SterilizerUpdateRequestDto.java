package iot.server.model.requestDto.sterilizer;

import iot.server.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SterilizerUpdateRequestDto {

    @NotEmpty(message = "도시는 필수입니다.")
    private String city;
    @NotEmpty(message = "도로명 주소는 필수입니다.")
    private String street;
    @NotEmpty(message = "위치는 필수 입니다.")
    private String position;
    @NotEmpty(message = "위치 코드는 필수입니다.")
    private String code;
}
