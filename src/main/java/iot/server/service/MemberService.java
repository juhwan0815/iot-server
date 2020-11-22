package iot.server.service;

import iot.server.advice.exception.EmailExistedException;
import iot.server.advice.exception.NotOwnerException;
import iot.server.advice.exception.UserNotFoundException;
import iot.server.domain.Member;
import iot.server.model.requestDto.member.MemberDeleteDto;
import iot.server.model.requestDto.member.MemberSignupDto;
import iot.server.model.requestDto.member.MemberUpdateRequestDto;
import iot.server.model.responseDto.member.MemberDto;
import iot.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberSignupDto memberSignupDto){

        if(memberRepository.findByEmail(memberSignupDto.getEmail()).isPresent()){
            throw new EmailExistedException();
        }else {
            Member member = new Member(
                    memberSignupDto.getEmail(),
                    passwordEncoder.encode(memberSignupDto.getPassword()),
                    memberSignupDto.getName());
            member.setRoles(Collections.singletonList("ROLE_MEMBER"));
            memberRepository.save(member);
        }
    }

    public List<MemberDto> findAll() {
        List<Member> findMembers = memberRepository.findAll();
        List<MemberDto> result = findMembers.stream()
                .map(m -> new MemberDto(
                        m.getId(),
                        m.getEmail(),
                        m.getPassword(),
                        m.getName(),
                        m.getRoles()))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional
    public void updateMember(String loginEmail, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member findMember = memberRepository.findByEmail(memberUpdateRequestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        System.out.println("1");
        if(!loginEmail.equals(findMember.getEmail())){
            System.out.println("2");
            throw new NotOwnerException();
        }
        findMember.changeMember(memberUpdateRequestDto.getName()
                ,passwordEncoder.encode(memberUpdateRequestDto.getPassword()));
    }

    @Transactional
    public void deleteMember(String loginEmail, MemberDeleteDto memberDeleteDto) {
        if(!loginEmail.equals(memberDeleteDto.getEmail())){
            throw new NotOwnerException();
        }
        memberRepository.deleteByEmail(memberDeleteDto.getEmail());
    }


    public MemberDto findOne(Long id){
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new MemberDto(member.getId(), member.getEmail(), member.getPassword(), member.getName(), member.getRoles());
    }
}
