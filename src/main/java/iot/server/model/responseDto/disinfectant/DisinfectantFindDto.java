package iot.server.model.responseDto.disinfectant;

import iot.server.domain.Disinfectant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisinfectantFindDto {

    private Long id;
    private int currentCapacity;
    private int totalCapacity;
    private int percent;

    public DisinfectantFindDto(Disinfectant disinfectant) {
        this.id = disinfectant.getId();
        this.currentCapacity = disinfectant.getCurrentCapacity();
        this.totalCapacity = disinfectant.getTotalCapacity();
        this.percent = (int)((double)currentCapacity/(double)totalCapacity * 100);
    }
}
