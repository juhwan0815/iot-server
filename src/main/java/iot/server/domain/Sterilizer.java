package iot.server.domain;

import lombok.Getter;

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

    @OneToMany(mappedBy = "sterilizer")
    private List<UseHistory> useHistories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "sterilizer")
    private List<ChangeHistory> changeHistories = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISINFECTANT_ID")
    private Disinfectant disinfectant;

    @Enumerated(EnumType.STRING)
    private RunStatus status;

    @Embedded
    private Address address;

}