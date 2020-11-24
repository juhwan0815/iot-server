package iot.server.service;

import iot.server.advice.exception.SterilizerNotFoundException;
import iot.server.domain.ChangeHistory;
import iot.server.domain.Sterilizer;
import iot.server.model.requestDto.disinfectant.DisinfectantSaveDto;
import iot.server.model.responseDto.changehistory.ChangeHistoryDto;
import iot.server.repository.ChangeHistoryRepository;
import iot.server.repository.SterilizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChangeHistoryService {

    private final ChangeHistoryRepository changeHistoryRepository;
    private final SterilizerRepository sterilizerRepository;

    @Transactional
    public void saveChangeHistory(DisinfectantSaveDto disinfectantSaveDto) {
        Sterilizer findSterilizer = sterilizerRepository.findBySerialNumber(disinfectantSaveDto.getSerialNumber()).orElseThrow(SterilizerNotFoundException::new);
        ChangeHistory changeHistory = new ChangeHistory();
        changeHistory.changeSterilizer(findSterilizer);
        changeHistoryRepository.save(changeHistory);
    }


    public List<ChangeHistoryDto> findChangeHistory(Long sterilizerId) {
        Sterilizer sterilizer = sterilizerRepository.findById(sterilizerId).orElseThrow(SterilizerNotFoundException::new);
        return sterilizer.getChangeHistories().stream().map(changeHistory -> new ChangeHistoryDto(changeHistory.getId(), changeHistory.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
