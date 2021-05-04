package indi.chime.base.controller;

import indi.chime.base.security.utils.JwtUtil;
import indi.chime.base.utils.ApiResultUtil;
import indi.chime.base.vo.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @PostMapping("")
    public ApiResult<String> get(@RequestBody Map<String, Object> data) {
        return ApiResultUtil.getSuccessApiResult(JwtUtil.createJwtToken(data));
    }

    @GetMapping("")
    public ApiResult<Map<String, Object>> get(@RequestHeader("Authorization") String Authorization) {
        return ApiResultUtil.getSuccessApiResult(JwtUtil.getJwtTokenClaims(Authorization));
    }
}
