package iot.server.service;

import iot.server.advice.exception.SterilizerNotFoundException;
import iot.server.advice.exception.SterilizerNotRunException;
import iot.server.domain.RunStatus;
import iot.server.domain.Sterilizer;
import iot.server.domain.UseHistory;
import iot.server.model.requestDto.usehistory.UseHistorySaveDto;
import iot.server.model.responseDto.userHistory.UseHistoryByDateDto;
import iot.server.repository.SterilizerRepository;
import iot.server.repository.UseHistoryRepository;
import iot.server.repository.UserHistoryRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserHistoryService {

    private final UseHistoryRepository useHistoryRepository;
    private final SterilizerRepository sterilizerRepository;
    private final UserHistoryRepositoryOld useHistoryRepositoryOld;

    @Transactional
    public void saveUserHistory(UseHistorySaveDto useHistorySaveDto) {
        Sterilizer findSterilizer = sterilizerRepository.findBySerialNumber(useHistorySaveDto.getSerialNumber()).orElseThrow(SterilizerNotFoundException::new);

        if(findSterilizer.getStatus().equals(RunStatus.NOTRUN)){
            throw new SterilizerNotRunException();
        }
        findSterilizer.getDisinfectant().changeCurrent(useHistorySaveDto.getUseAmount());

        UseHistory useHistory = new UseHistory(useHistorySaveDto.getUseAmount());
        useHistory.changeSterilizer(findSterilizer);
        useHistoryRepository.save(useHistory);

    }


    public List<UseHistoryByDateDto> findUseHistoryByDate(Long sterilizerId) {
        Sterilizer sterilizer = sterilizerRepository.findById(sterilizerId).orElseThrow(SterilizerNotFoundException::new);
        return useHistoryRepositoryOld.findUseHistoryByDate(sterilizer);
    }
}
