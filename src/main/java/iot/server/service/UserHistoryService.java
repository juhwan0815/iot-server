package iot.server.service;

import iot.server.advice.exception.SterilizerNotFoundException;
import iot.server.advice.exception.SterilizerNotRunException;
import iot.server.domain.RunStatus;
import iot.server.domain.Sterilizer;
import iot.server.domain.UseHistory;
import iot.server.model.requestDto.userhistory.UserHistorySaveDto;
import iot.server.repository.SterilizerRepository;
import iot.server.repository.UseHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserHistoryService {

    private final UseHistoryRepository useHistoryRepository;
    private final SterilizerRepository sterilizerRepository;

    @Transactional
    public void saveUserHistory(UserHistorySaveDto useHistorySaveDto) {
        Sterilizer findSterilizer = sterilizerRepository.findBySerialNumber(useHistorySaveDto.getSerialNumber()).orElseThrow(SterilizerNotFoundException::new);

        if(findSterilizer.getStatus().equals(RunStatus.NOTRUN)){
            throw new SterilizerNotRunException();
        }
        findSterilizer.getDisinfectant().changeCurrent(useHistorySaveDto.getUseAmount());

        UseHistory useHistory = new UseHistory(useHistorySaveDto.getUseAmount());
        useHistory.changeSterilizer(findSterilizer);
        useHistoryRepository.save(useHistory);

    }
}
