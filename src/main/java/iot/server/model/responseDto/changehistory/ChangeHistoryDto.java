package iot.server.model.responseDto.changehistory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangeHistoryDto {

    private Long changeHistoryId;
    private LocalDateTime changeTime;

    public ChangeHistoryDto(Long changeHistoryId, LocalDateTime changeTime) {
        this.changeHistoryId = changeHistoryId;
        this.changeTime = changeTime;
    }
}
