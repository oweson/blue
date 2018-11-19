package com.online.college.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserCourseSection;
import com.online.college.core.user.domain.UserCourseSectionDto;


public interface IUserCourseSectionService {

    /**
     * 1  根据id获取
     **/
    UserCourseSection getById(Long id);

    /**
     * 2 获取所有
     **/
    List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    /**
     * 3 获取最新的
     */
    UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     * 4 分页获取
     **/
    TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity, TailPage<UserCourseSectionDto> page);

    /**
     * 5 创建
     **/
    void createSelectivity(UserCourseSection entity);

    /**
     * 6 根据id更新
     **/
    void update(UserCourseSection entity);

    /**
     * 7 根据id 进行可选性更新
     **/
    void updateSelectivity(UserCourseSection entity);

    /**
     * 8 物理删除
     **/
    void delete(UserCourseSection entity);

    /**
     * 9逻辑删除
     **/
    void deleteLogic(UserCourseSection entity);


}

