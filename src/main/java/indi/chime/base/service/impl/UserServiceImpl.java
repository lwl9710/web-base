package indi.chime.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.chime.base.entities.User;
import indi.chime.base.service.UserService;
import indi.chime.base.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户管理实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}




