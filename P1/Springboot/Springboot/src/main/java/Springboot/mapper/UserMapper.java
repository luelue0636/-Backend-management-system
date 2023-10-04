package Springboot.mapper;

import Springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-21
 */
@Mapper

public interface UserMapper extends BaseMapper<User> {

}
