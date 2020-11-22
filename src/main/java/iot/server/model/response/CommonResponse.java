package iot.server.model.response;

public enum CommonResponse {
    SUCCESS(0,"성공"),
    FAIL(-1,"실패");

    int code;
    String msg;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
