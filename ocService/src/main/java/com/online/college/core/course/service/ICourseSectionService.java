package com.online.college.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseSection;


public interface ICourseSectionService {

    /**
     * 1 根据id获取
     **/
    CourseSection getById(Long id);

    /**
     * 2  获取所有
     **/
    List<CourseSection> queryAll(CourseSection queryEntity);

    /**
     * 3 获取课程章最大的sort
     */
    Integer getMaxSort(Long courseId);

    /**
     * 4 分页获取
     **/
    TailPage<CourseSection> queryPage(CourseSection queryEntity, TailPage<CourseSection> page);

    /**
     * 5 创建
     **/
    void createSelectivity(CourseSection entity);

    /**
     * 6 批量创建
     **/
    void createList(List<CourseSection> entityList);

    /**
     * 7 根据id更新
     **/
    void update(CourseSection entity);

    /**
     * 8 根据id 进行可选性更新
     **/
    void updateSelectivity(CourseSection entity);

    /**
     * 9 物理删除
     **/
    void delete(CourseSection entity);

    /**
     * 10 逻辑删除
     **/
    void deleteLogic(CourseSection entity);

    /**
     * 11 比当前sort大的，正序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    CourseSection getSortSectionMax(CourseSection curCourseSection);

    /**
     * 12 比当前sort小的，倒序排序的第一个
     *
     * @param curCourseSection
     * @return
     */
    CourseSection getSortSectionMin(CourseSection curCourseSection);
}

