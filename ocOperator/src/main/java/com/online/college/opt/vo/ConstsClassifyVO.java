package com.online.college.opt.vo;

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

    private static final long serialVersionUID = -6898939223836635781L;

    /**子分类列表*/
    private List<ConstsClassify> subClassifyList = new ArrayList<ConstsClassify>();

    /**课程推荐列表*/
    private List<Course> recomdCourseList;
    //todo 5 ***大的bug加载出错：jrebel回家再两个文件，虽然tomcat部署一个war包？？？不热部署，直接tomcat也可以！！！***


}
