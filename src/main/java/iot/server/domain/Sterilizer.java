package iot.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Sterilizer extends CommonData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STERILIZER_ID")
    private Long id;

    @OneToMany(mappedBy = "sterilizer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UseHistory> useHistories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "sterilizer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChangeHistory> changeHistories = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "DISINFECTANT_ID")
    private Disinfectant disinfectant;

    @Enumerated(EnumType.STRING)
    private RunStatus status;

    private String serialNumber;

    @Embedded
    private Address address;

    public Sterilizer(RunStatus status, Address address,String serialNumber) {
        this.status = status;
        this.address = address;
        this.serialNumber = serialNumber;
    }

    public Sterilizer() {}

    // == 연관관계 편이 메서드 == //
    public void changeMember(Member member){
        this.member = member;
        member.getSterilizers().add(this);
    }

    public void changeDisinfectant(Disinfectant disinfectant){
        this.disinfectant = disinfectant;
        disinfectant.setSterilizer(this);
    }
    // == 비즈니스 로직 == //
    // 주소 변경
    public void changeAddress(Address address){
        this.address = address;
    }

    public void noRunStatus(){
        this.status = RunStatus.NOTRUN;
    }

    public void runStatus(){
        this.status = RunStatus.RUN;
        System.out.println(status);
    }
}