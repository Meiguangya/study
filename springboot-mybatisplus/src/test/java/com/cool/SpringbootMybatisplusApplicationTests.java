package com.cool;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cool.entity.SysUser;
import com.cool.entity.TestEnum;
import com.cool.enums.StepEnum;
import com.cool.mapper.SysUserMapper;
import com.cool.mapper.TestEnumMapper;
import com.cool.service.ISysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    TestEnumMapper testEnumMapper;

    @Test
    void contextLoads() {
        System.out.println("11");
    }

    @Test
    void testAddTestEnum() {
        TestEnum t1 = new TestEnum();
        t1.setId(2);
        t1.setStep(StepEnum.STEP_2);
        testEnumMapper.insert(t1);
        System.out.println("over");
    }


    @Test
    void testUser01() {
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
//        qw.eq("nick_name","测试号");
//        qw.ne("username","test");
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "admin");
        map.put("qq_oppen_id", null);
        qw.allEq(map,false);
        List<SysUser> list = sysUserService.list(qw);
        list.forEach(System.out::println);
    }


    @Test
    void testUser02(){
        UpdateWrapper<SysUser> uw = new UpdateWrapper<>();
        uw.eq("id",1);
        uw.set("qq_oppen_id",null);
        sysUserService.update(uw);
    }

    @Test
    void testUser03(){
        //sysUserService.save();
    }




}
