package iot.server.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    private boolean success; // 응답 성공여부

    private int code; // 응답 코드 번호

    private String msg; // 응답 메세지
}
