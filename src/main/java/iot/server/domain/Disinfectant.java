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

    public void changeCurrent(int useAmount) {
        if (currentCapacity-useAmount <= 0){
            currentCapacity = 0;
            sterilizer.noRunStatus();
        }else {
            currentCapacity -= useAmount;
        }
    }
}
