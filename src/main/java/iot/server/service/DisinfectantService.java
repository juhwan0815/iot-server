package iot.server.service;

import iot.server.advice.exception.SterilizerNotFoundException;
import iot.server.domain.Disinfectant;
import iot.server.domain.Sterilizer;
import iot.server.model.requestDto.disinfectant.DisinfectantSaveDto;
import iot.server.repository.DisinfectantRepository;
import iot.server.repository.SterilizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DisinfectantService {

    private final DisinfectantRepository disinfectantRepository;
    private final SterilizerRepository sterilizerRepository;

    @Transactional
    public void saveDisinfectant(DisinfectantSaveDto disinfectantSaveDto) {
        Sterilizer findSterilizer = sterilizerRepository.findBySerialNumber(disinfectantSaveDto.getSerialNumber()).orElseThrow(SterilizerNotFoundException::new);
        Disinfectant disinfectant = new Disinfectant(disinfectantSaveDto.getStartWeight(),disinfectantSaveDto.getStartWeight());
        findSterilizer.changeDisinfectant(disinfectant);
        findSterilizer.runStatus();
        disinfectantRepository.save(disinfectant);
    }
}
