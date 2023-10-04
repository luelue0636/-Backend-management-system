package Springboot.service.impl;

import Springboot.entity.Menu;
import Springboot.mapper.MenuMapper;
import Springboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
