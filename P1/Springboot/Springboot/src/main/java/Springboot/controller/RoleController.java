package Springboot.controller;


import Springboot.common.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import Springboot.service.IRoleService;
import Springboot.entity.Role;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-24
 */
@RestController
@RequestMapping("/role")
public class RoleController {


@Resource
private IRoleService roleService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Role role) {return Result.success(roleService.saveOrUpdate(role));}

@DeleteMapping("/{id}")
public Result  delete(@PathVariable Integer id) {
        return Result.success(roleService.removeById(id));
        }

@PostMapping("/del/batch")
public Result  deleteBatch(@RequestBody List<Integer> ids) {return Result.success( roleService.removeByIds(ids));}

@GetMapping
public Result  findAll() {return Result.success(roleService.list());}

@GetMapping("/{id}")
public Result  findOne(@PathVariable Integer id) {return Result.success( roleService.getById(id));}

@GetMapping("/page")
public Result findPage(@RequestParam String name,
                       @RequestParam Integer pageNum,
                       @RequestParam Integer pageSize
                       ) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
}
//绑定角色
        @PostMapping("/roleMenu/{roleId}")
        public Result roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId,menuIds);
        return Result.success();
}
/*
绑定角色和菜单关系
 */
        @GetMapping("/roleMenu/{roleId}")
        public Result getRoleMenu(@PathVariable Integer roleId) {
                return Result.success(roleService.getRoleMenu(roleId));
        }
}

