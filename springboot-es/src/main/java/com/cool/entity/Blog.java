package com.cool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author water33
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    private String title;

    private String author;

    private String content;


    //@JsonFormat(pattern = "yyyyMMdd HH-mm-ss",timezone="GMT+8")
    //@DateTimeFormat(pattern = "yyyyMMdd HH-mm-ss")
    private LocalDateTime createTime;


    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
