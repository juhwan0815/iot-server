package iot.server.model.responseDto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDto {

    private Long id;
    private String email;

    @JsonIgnore
    private String password;

    private String name;
    private List<String> roles;

    public MemberDto(Long id, String email, String password, String name, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }
}
