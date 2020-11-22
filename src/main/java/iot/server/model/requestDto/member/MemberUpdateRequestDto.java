package iot.server.model.requestDto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberUpdateRequestDto {

    @NotEmpty
    private String email;
    private String password;
    private String name;

}
