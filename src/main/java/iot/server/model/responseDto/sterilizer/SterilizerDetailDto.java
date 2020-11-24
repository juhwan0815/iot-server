package iot.server.model.responseDto.sterilizer;

import iot.server.domain.Address;
import iot.server.domain.RunStatus;
import iot.server.domain.Sterilizer;
import iot.server.model.responseDto.disinfectant.DisinfectantFindDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SterilizerDetailDto {

    private Long id;
    private Address address;
    private String serialNumber;
    private RunStatus runStatus;
    private DisinfectantFindDto disinfectant;

    public SterilizerDetailDto(Sterilizer sterilizer) {
        this.id = sterilizer.getId();
        this.address = sterilizer.getAddress();
        this.serialNumber = sterilizer.getSerialNumber();
        this.runStatus = sterilizer.getStatus();
        if(sterilizer.getDisinfectant() == null){
            this.disinfectant = null;
        }else{
            this.disinfectant = new DisinfectantFindDto(sterilizer.getDisinfectant());
        }
    }
}
