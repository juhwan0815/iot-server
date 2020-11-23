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

    // == 연관관계 편이 메서드 == //
    public void changeSterilizer(Sterilizer sterilizer){
        this.sterilizer = sterilizer;
        sterilizer.getChangeHistories().add(this);
    }
}
