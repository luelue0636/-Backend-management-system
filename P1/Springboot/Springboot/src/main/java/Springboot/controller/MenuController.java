package Springboot.controller;


import Springboot.common.Constants;
import Springboot.common.Result;
import Springboot.entity.Dict;
import Springboot.entity.Role;
import Springboot.mapper.DictMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import Springboot.service.IMenuService;
import Springboot.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

import static java.awt.SystemColor.menu;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 朱嘉怡
 * @since 2023-08-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


        @Resource
        private IMenuService menuService;

        @Resource
        private DictMapper dictMapper;


        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Menu menu) {
                return Result.success(menuService.saveOrUpdate(menu));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(menuService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(menuService.removeByIds(ids));
        }
        @GetMapping
        public Result findAll(@RequestParam(defaultValue = "") String name) {
                QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name",name);
        //查询所以数据
                List<Menu> list =menuService.list(queryWrapper);
                //找出pid为null的一级菜单
                List<Menu> parentNode=list.stream().filter(menu->menu.getPid()==null).collect(Collectors.toList());
                for(Menu menu :parentNode){
                        menu.setChildren( list.stream().filter(m->menu.getId().equals( m.getPid())).collect(Collectors.toList()));

                }

                return Result.success(parentNode);
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(menuService.getById(id));
        }


        @GetMapping("/page")
        public Result findPage(@RequestParam String name,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize
        ) {
                QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name",name);
                queryWrapper.orderByDesc("id");
                return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
        @GetMapping("/icons")
        public Result getIcons() {
                QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
                return Result.success(dictMapper.selectList(queryWrapper));
        }


}

