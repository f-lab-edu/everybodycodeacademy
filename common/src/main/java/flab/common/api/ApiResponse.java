package flab.common.api;


public class ApiResponse {
    private String message;
    private int status;

    // 기본 생성자
    public ApiResponse() {
    }

    // 전체 필드를 받는 생성자
    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getter / Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}