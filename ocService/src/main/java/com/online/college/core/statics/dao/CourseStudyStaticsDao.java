package com.online.college.core.statics.dao;

import java.util.List;

import com.online.college.core.statics.domain.CourseStudyStaticsDto;

public interface CourseStudyStaticsDao {

    /**
     * 1统计课程学习情况
     **/
    List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);

}

