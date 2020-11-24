package iot.server.model.responseDto.sterilizer;

import io.swagger.models.auth.In;
import iot.server.domain.Address;
import iot.server.domain.RunStatus;
import iot.server.domain.Sterilizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SterilizerDto {

    private Long id;
    private RunStatus runStatus;
    private Integer capacity;

    public SterilizerDto(Sterilizer sterilizer) {
        this.id = sterilizer.getId();
        this.runStatus = sterilizer.getStatus();
        if(sterilizer.getDisinfectant()==null) {
            this.capacity = null;
        }else{
            int currentCapacity = sterilizer.getDisinfectant().getCurrentCapacity();
            int totalCapacity = sterilizer.getDisinfectant().getTotalCapacity();
            this.capacity = (int)((double)currentCapacity/(double)totalCapacity * 100);
        }
    }
}
