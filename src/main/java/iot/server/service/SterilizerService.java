package iot.server.service;

import iot.server.advice.exception.DuplicateSterilizerSerialNumberException;
import iot.server.advice.exception.NotOwnerException;
import iot.server.advice.exception.SterilizerNotFoundException;
import iot.server.advice.exception.UserNotFoundException;
import iot.server.domain.Address;
import iot.server.domain.Member;
import iot.server.domain.RunStatus;
import iot.server.domain.Sterilizer;
import iot.server.model.requestDto.sterilizer.SterilizerSaveRequestDto;
import iot.server.model.requestDto.sterilizer.SterilizerUpdateRequestDto;
import iot.server.model.responseDto.sterilizer.SterilizerDetailDto;
import iot.server.model.responseDto.sterilizer.SterilizerDto;
import iot.server.repository.MemberRepository;
import iot.server.repository.SterilizerRepository;
import iot.server.repository.SterilizerRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SterilizerService {

    private final SterilizerRepository sterilizerRepository;
    private final MemberRepository memberRepository;
    private final SterilizerRepositoryOld sterilizerRepositoryOld;

    @Transactional
    public void saveSterilizer(String loginEmail, SterilizerSaveRequestDto sterilizerSaveRequestDto) {
        Member member = memberRepository.findByEmail(loginEmail).orElseThrow(UserNotFoundException::new);
        if(sterilizerRepository.findBySerialNumber(sterilizerSaveRequestDto.getSerialNumber()).isPresent()) {
            throw new DuplicateSterilizerSerialNumberException();
        }
        Address address = new Address(sterilizerSaveRequestDto.getCity(),sterilizerSaveRequestDto.getStreet()
        ,sterilizerSaveRequestDto.getCode(),sterilizerSaveRequestDto.getPosition());
        Sterilizer sterilizer = new Sterilizer(RunStatus.NOTRUN,address, sterilizerSaveRequestDto.getSerialNumber());
        sterilizer.changeMember(member);
        sterilizerRepository.save(sterilizer);
    }

    public List<SterilizerDto> findSterilizers(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        List<Sterilizer> findSterilizer = sterilizerRepositoryOld.findSterilizerByMember(member);
        return findSterilizer.stream().map(sterilizer -> new SterilizerDto(sterilizer))
                .collect(Collectors.toList());
    }

    public SterilizerDetailDto findSterilizer(Long sterilizerId) {
        Sterilizer findSterilizer = sterilizerRepository.findById(sterilizerId).orElseThrow(SterilizerNotFoundException::new);
        return new SterilizerDetailDto(findSterilizer);
    }

    @Transactional
    public void updateSterilizer(Long sterilizerId, SterilizerUpdateRequestDto requestDto,String loginEmail) {
        Sterilizer findSterilizer = sterilizerRepository.findById(sterilizerId).orElseThrow(SterilizerNotFoundException::new);
        if(!loginEmail.equals(findSterilizer.getMember().getEmail())){
            throw new NotOwnerException();
        }
        Address address = new Address(requestDto.getCity(),requestDto.getStreet(), requestDto.getCode(), requestDto.getPosition());
        findSterilizer.changeAddress(address);
    }

    @Transactional
    public void deleteSterilizer(Long sterilizerId, String loginEmail) {
        Sterilizer findSterilizer = sterilizerRepository.findById(sterilizerId).orElseThrow(SterilizerNotFoundException::new);
        if(!loginEmail.equals(findSterilizer.getMember().getEmail())){
            throw new NotOwnerException();
        }

        sterilizerRepository.deleteById(sterilizerId);
    }
}
