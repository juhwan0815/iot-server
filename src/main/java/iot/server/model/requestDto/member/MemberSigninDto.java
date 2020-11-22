package iot.server.model.requestDto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberSigninDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
