package iot.server.model.responseDto.userHistory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class UseHistoryByDateDto {

    private LocalDate date;
    private Long sumOfUseAmount;
    private Long useCount;

    public UseHistoryByDateDto(LocalDate date,Long sumOfUseAmount,Long useCount) {
        this.date = date;
        this.sumOfUseAmount = sumOfUseAmount;
        this.useCount = useCount;
    }
}
