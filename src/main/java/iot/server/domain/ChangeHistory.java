package iot.server.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ChangeHistory extends CommonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHANGEHISTORY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STERILIZER_ID")
    private Sterilizer sterilizer;
}
