package com.cool.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author meiguangya
 * @Description
 * @create 2021-07-13 20:46
 */
public enum StepEnum {

    STEP_1("STOP_1","第一步"),
    STEP_2("STOP_2","第一步");


    @EnumValue
    String stepName;
    String desc;

    StepEnum(String stepName, String desc) {
        this.stepName = stepName;
        this.desc = desc;
    }
}
