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

    public UseHistory() {
    }

    public UseHistory(int useAmount) {
        this.useAmount = useAmount;
    }

    // 연관관계 편이 메서드 //
    public void changeSterilizer(Sterilizer sterilizer){
        this.sterilizer = sterilizer;
        sterilizer.getUseHistories().add(this);
    }
}
