package indi.chime.base.utils;

import indi.chime.base.enums.ApiCodeEnum;
import indi.chime.base.vo.ApiResult;

/**
 * 通用api返回工具
 */
public class ApiResultUtil {
    // 获取成功结果
    public static <T> ApiResult<T> getSuccessApiResult(T data) {
        return getApiResultByEnum(ApiCodeEnum.SUCCESS, data);
    }
    // 获取警告结果
    public static <T> ApiResult<T> getWarningApiResult(T data) {
        return getApiResultByEnum(ApiCodeEnum.WARNING, data);
    }
    // 获取错误结果
    public static <T> ApiResult<T> getFailureApiResult(T data) {
        return getApiResultByEnum(ApiCodeEnum.FAILURE, data);
    }
    // 获取指定枚举结果
    public static <T> ApiResult<T> getApiResultByEnum(ApiCodeEnum apiCodeEnum, T data) {
        return new ApiResult<>(apiCodeEnum.getCode(), apiCodeEnum.getMessage(), data);
    }
    // 获取结果
    public static <T> ApiResult<T> getApiResult(Integer code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }
}
