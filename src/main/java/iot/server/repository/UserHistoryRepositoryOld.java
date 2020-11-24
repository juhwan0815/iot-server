package iot.server.repository;

import iot.server.domain.Sterilizer;
import iot.server.model.responseDto.userHistory.UseHistoryByDateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserHistoryRepositoryOld {

    @PersistenceContext
    private EntityManager em;


    public List<UseHistoryByDateDto> findUseHistoryByDate(Sterilizer sterilizer) {
        return em.createQuery("select new iot.server.model.responseDto.userHistory.UseHistoryByDateDto(uh.createdDate,sum(uh.useAmount),count(uh.useAmount)) " +
                " from UseHistory uh where uh.sterilizer= :sterilizer group by uh.createdDate order by uh.createdDate desc ",UseHistoryByDateDto.class)
                .setParameter("sterilizer",sterilizer)
                .getResultList();
    }
}
