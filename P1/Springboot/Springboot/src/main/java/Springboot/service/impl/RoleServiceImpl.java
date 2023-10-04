package Springboot.service.impl;

import Springboot.entity.Role;
import Springboot.entity.RoleMenu;
import Springboot.mapper.RoleMapper;
import Springboot.mapper.RoleMenuMapper;
import Springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        // 删除角色已有的菜单
        roleMenuMapper.deleteByRoleId(roleId);

        // 插入新的角色菜单关联
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId){
        return roleMenuMapper.selectByRoleId(roleId);
    }

}
