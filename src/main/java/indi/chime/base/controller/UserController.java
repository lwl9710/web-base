package indi.chime.base.controller;

import indi.chime.base.entities.User;
import indi.chime.base.service.UserService;
import indi.chime.base.utils.ApiResultUtil;
import indi.chime.base.utils.DigestUtil;
import indi.chime.base.vo.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取用户列表
     */
    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ApiResult<List<User>> list() {
        return ApiResultUtil.getSuccessApiResult(userService.list());
    }

    /**
     * 添加用户
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody User user) {
        Date currentDate = new Date();
        user.setPassword(DigestUtil.getMD5String(user.getPassword()));
        user.setUpdateTime(currentDate);
        user.setCreateTime(currentDate);
        userService.save(user);
        return ApiResultUtil.getSuccessApiResult("添加成功");
    }
}
