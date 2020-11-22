package iot.server.service;

import iot.server.advice.exception.UserNotFoundException;
import iot.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberPk){
        return memberRepository.findById(Long.valueOf(memberPk)).orElseThrow(UserNotFoundException::new);
    }
}
