package indi.chime.base.controller;

import indi.chime.base.utils.ApiResultUtil;
import indi.chime.base.vo.ApiResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Redis管理
 */
@Slf4j
@Api(tags = "Redis管理")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加Redis值
     * @param key 键名
     * @param value 键值
     */
    @ApiOperation("添加Redis值")
    @PostMapping("/{key}/{value}")
    public ApiResult<String> put(@PathVariable("key")  @ApiParam("键名") String key, @PathVariable("value")  @ApiParam("键值") String value) {
        log.info("设置键名：{}, 设置键值：{}", key,value);
        redisTemplate.opsForValue().set(key, value);
        return ApiResultUtil.getSuccessApiResult("设置成功");
    }

    /**
     * 获取Redis值
     * @param key 键名
     */
    @ApiOperation("获取Redis值")
    @GetMapping("/{key}")
    public ApiResult<String> get(@PathVariable("key") @ApiParam("键名") String key) {
        Object value = redisTemplate.opsForValue().get(key);
        log.info("获取的值为：{}", value);
        return ApiResultUtil.getSuccessApiResult((String) value);
    }
}
