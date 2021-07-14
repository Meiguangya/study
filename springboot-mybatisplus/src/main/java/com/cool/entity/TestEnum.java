package com.cool.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.cool.enums.StepEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author water33
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestEnum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    private StepEnum step;


}
