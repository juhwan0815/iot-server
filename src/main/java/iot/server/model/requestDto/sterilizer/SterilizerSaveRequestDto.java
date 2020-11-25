package iot.server.model.requestDto.sterilizer;

import iot.server.domain.Address;
import iot.server.domain.RunStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SterilizerSaveRequestDto {

    @NotBlank(message = "시리얼번호는 필수입니다.")
    private String serialNumber;
    @NotEmpty(message = "도시는 필수입니다.")
    private String city;
    @NotEmpty(message = "도로명 주소는 필수입니다.")
    private String street;
    @NotEmpty(message = "위치는 필수 입니다.")
    private String position;
    @NotEmpty(message = "위치 코드는 필수입니다.")
    private String code;
}
