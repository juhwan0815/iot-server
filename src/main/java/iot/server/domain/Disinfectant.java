package iot.server.domain;

import lombok.Getter;

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

    private int price;

    private String name;

    @OneToOne(mappedBy = "disinfectant")
    private Sterilizer sterilizer;



}
