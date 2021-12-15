package internaltest.springsecuritylogin.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapper {

    private String code;
    private String message;
    private List<ResponseDto> contents;

    public ResponseWrapper() {
        this.contents = new ArrayList<>();
    }

    public ResponseWrapper(String code, String message) {
        this.code = code;
        this.message = message;
        this.contents = new ArrayList<>();
    }

    public ResponseWrapper(String code, String message, List<ResponseDto> contents) {
        this.code = code;
        this.message = message;
        this.contents = contents;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseDto> getContents() {
        return contents;
    }

    public void setContents(List<ResponseDto> contents) {
        this.contents = contents;
    }

    @JsonIgnore
    public void appendResponseContent(ResponseDto responseDto) {
        contents.add(responseDto);
    }

}
