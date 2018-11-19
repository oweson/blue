package com.online.college.core.course.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseComment;


public interface CourseCommentDao {

    /**
     * 1 根据id获取
     **/
    CourseComment getById(Long id);

    /**
     * 2 获取所有
     **/
    List<CourseComment> queryAll(CourseComment queryEntity);

    /**
     * 3 获取总数量
     **/
    Integer getTotalItemsCount(CourseComment queryEntity);

    /**
     * 4 分页获取
     **/
    List<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page);


    /**
     * 5 获取总数量
     **/
    Integer getMyQAItemsCount(CourseComment queryEntity);

    /**
     * 6 分页获取
     **/
    List<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page);

    /**
     * 7 创建新记录
     **/
    void create(CourseComment entity);

    /**
     * 8 创建新记录
     */
    void createSelectivity(CourseComment entity);

    /**
     * 9 根据id更新
     **/
    void update(CourseComment entity);

    /**
     * 10 根据id选择性更新自动
     **/
    void updateSelectivity(CourseComment entity);

    /**
     * 11 物理删除
     **/
    void delete(CourseComment entity);

    /**
     * 12 逻辑删除
     **/
    void deleteLogic(CourseComment entity);


}

