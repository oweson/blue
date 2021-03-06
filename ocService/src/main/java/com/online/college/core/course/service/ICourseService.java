package com.online.college.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;

/**
 * 课程服务层
 */
public interface ICourseService {

    /**
     * 1 根据id获取
     **/
    Course getById(Long id);

    /**
     * 2 获取所有
     **/
    List<Course> queryList(CourseQueryDto queryEntity);

    /**
     * 3 分页获取
     **/
    TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page);

    /**
     * 4 创建
     **/
    void createSelectivity(Course entity);

    /**
     * 5 根据id 进行可选性更新
     **/
    void updateSelectivity(Course entity);

    /**
     * 6 物理删除
     **/
    void delete(Course entity);

    /**
     * 7逻辑删除
     **/
    void deleteLogic(Course entity);

}

