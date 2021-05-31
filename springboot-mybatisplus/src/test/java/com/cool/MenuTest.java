package com.cool;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cool.entity.SysMenu;
import com.cool.mapper.SysMenuMapper;
import com.cool.mapper.SysUserMapper;
import com.cool.service.ISysMenuService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class MenuTest {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private ISysMenuService sysMenuService;

    @Test
    public void testAllMenu(){
        val allMenu = sysMenuMapper.selectList(null);
        allMenu.forEach(System.out::println);
    }

    //
    @Test
    public void testMenu() {
        val allMenu = sysMenuMapper.selectList(null);
        //allMenu.forEach(System.out::println);

        //get level-1 menu
        List<SysMenu> menus = allMenu.stream().filter(m -> m.getParentId().equals("0"))
            .collect(Collectors.toList());


        for (SysMenu menu : menus) {
            menu.setChildren(getChild(menu, allMenu));
        }

        //menus.forEach(System.out::println);
        String res = JSON.toJSONString(menus);
        System.out.println(res);
    }

    private List<SysMenu> getChild(SysMenu parent, List<SysMenu> allMenu) {
        List<SysMenu> menus = allMenu.stream()
            .filter(m -> m.getParentId().equals(parent.getId().toString()))
            .collect(Collectors.toList());

        for (SysMenu menu : menus) {
            menu.setChildren(getChild(menu, allMenu));
        }

        return menus;
    }



    @Test
    public void delMenu(){
//        boolean remove = sysMenuService.remove(new QueryWrapper<SysMenu>().eq("id", 1));
//        System.out.println(remove);

        int i = sysMenuMapper.deleteById(94);
        System.out.println(i);

    }

}
