package iot.server.model.requestDto.userhistory;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserHistorySaveDto {

    @NotEmpty
    private String serialNumber;

    @NotNull
    @Min(0)
    private Integer useAmount;
}
