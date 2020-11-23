package iot.server.model.responseDto.sterilizer;

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
    private String capacity;

    public SterilizerDto(Sterilizer sterilizer) {
        this.id = sterilizer.getId();
        this.runStatus = sterilizer.getStatus();
        if(sterilizer.getDisinfectant()==null) {
            this.capacity = null;
        }else{
            int capacity = (sterilizer.getDisinfectant().getCurrentCapacity() / sterilizer.getDisinfectant().getTotalCapacity()) * 100;
            this.capacity = String.valueOf(capacity);
        }
    }
}
