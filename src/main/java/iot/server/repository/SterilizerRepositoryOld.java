package iot.server.repository;

import iot.server.domain.Member;
import iot.server.domain.Sterilizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SterilizerRepositoryOld {

    @PersistenceContext
    private EntityManager em;

    public List<Sterilizer> findSterilizerByMember(Member member){
        return em.createQuery("select s from Sterilizer s left join fetch s.disinfectant d where s.member = :member", Sterilizer.class)
                .setParameter("member", member)
                .getResultList();
    }
}
