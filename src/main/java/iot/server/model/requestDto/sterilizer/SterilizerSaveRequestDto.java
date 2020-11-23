package iot.server.model.requestDto.sterilizer;

import iot.server.domain.Address;
import iot.server.domain.RunStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SterilizerSaveRequestDto {

    @NotEmpty
    private String serialNumber;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String position;
    @NotEmpty
    private String code;
}
