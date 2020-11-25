package iot.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Disinfectant extends CommonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISINFECTANT_ID")
    private Long id;

    private int totalCapacity;

    private int currentCapacity;

    @Setter
    @OneToOne(mappedBy = "disinfectant")
    private Sterilizer sterilizer;

    public Disinfectant() {
    }

    public Disinfectant(int totalCapacity, int currentCapacity) {
        this.totalCapacity = totalCapacity;
        this.currentCapacity = currentCapacity;
    }

    public int changeCurrentCapacity(int currentCapacity) {
        if(this.currentCapacity <= 20){
            sterilizer.noRunStatus();
            this.currentCapacity = 0;
            return 0;
        }else {
            int useAmount = this.currentCapacity - currentCapacity;
            this.currentCapacity = currentCapacity;
            return useAmount;
        }
    }
}
