package iot.server.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UseHistory extends CommonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USEHISTORY_ID")
    private Long id;

    private int useAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STERILIZER_ID")
    private Sterilizer sterilizer;
}
