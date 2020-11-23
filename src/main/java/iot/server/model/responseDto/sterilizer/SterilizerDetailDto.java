package iot.server.model.responseDto.sterilizer;

import iot.server.domain.Address;
import iot.server.domain.RunStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SterilizerDetailDto {

    private Long id;
    private Address address;
    private String serialNumber;
    private RunStatus runStatus;
}
