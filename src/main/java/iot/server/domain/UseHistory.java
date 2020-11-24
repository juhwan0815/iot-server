package iot.server.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UseHistory extends CommonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USEHISTORY_ID")
    private Long id;

    private int useAmount;

    @CreatedDate
    private LocalDate createdDate;

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
