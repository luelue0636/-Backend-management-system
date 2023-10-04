package Springboot.service;

import Springboot.common.Result;
import Springboot.controller.dto.UserDTO;
import Springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-21
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
