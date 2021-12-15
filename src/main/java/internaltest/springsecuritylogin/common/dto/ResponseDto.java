package internaltest.springsecuritylogin.common.dto;

public class ResponseDto {

    private String name;
    private Object content;

    public ResponseDto() {
    }

    public ResponseDto(String name, Object content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
