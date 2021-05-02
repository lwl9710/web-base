package indi.chime.base.enums;

/**
 * 常用ApiCode
 */
public enum ApiCodeEnum {
    // 成功
    SUCCESS(200, "请求成功"),
    // 警告
    WARNING(100, "请求警告"),
    // 错误
    FAILURE(400, "请求错误");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态信息
     */
    private final String message;

    ApiCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiCodeEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
