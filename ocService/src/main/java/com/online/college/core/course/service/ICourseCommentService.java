package com.online.college.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseComment;


public interface ICourseCommentService {


    /**
     * 1 分页获取
     **/
    TailPage<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 2 分页获取我的所有课程的qa
     */
    TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 3 创建
     **/
    void create(CourseComment entity);

    /**
     * 4 创建
     */
    void createSelectivity(CourseComment entity);

    /**
     * 5 根据id更新
     **/
    void update(CourseComment entity);

    /**
     * 6 根据id 进行可选性更新
     **/
    void updateSelectivity(CourseComment entity);

    /**
     * 7 物理删除
     **/
    void delete(CourseComment entity);

    /**
     * 8 逻辑删除
     **/
    void deleteLogic(CourseComment entity);

    /**
     * 9 根据id获取
     **/
    CourseComment getById(Long id);

    /**
     * 10 获取所有
     **/
    List<CourseComment> queryAll(CourseComment queryEntity);


}

