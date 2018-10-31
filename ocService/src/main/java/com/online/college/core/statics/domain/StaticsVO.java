package com.online.college.core.statics.domain;

import lombok.Data;

import java.util.List;

/**
 * 报表分析VO
 */
@Data
public class StaticsVO {

    /**
     * 主标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 分类
     */
    private List<String> categories;

    /**
     * 数据
     */
    private List<Integer> data;


}
