package com.online.college.portal.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.course.domain.Course;
import lombok.Data;

/**
 * 页面展示 value object
 */
@Data
public class ConstsClassifyVO extends ConstsClassify {
    /**拥有一级分类的属性*/

    private static final long serialVersionUID = -6898939223836635781L;

    /**子分类列表，二级分类的填充*/
    private List<ConstsClassify> subClassifyList = new ArrayList<ConstsClassify>();

    //课程推荐列表
    private List<Course> recomdCourseList;


}
