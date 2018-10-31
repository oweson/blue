package com.online.college.opt.business;

import java.util.List;

import com.online.college.opt.vo.CourseSectionVO;

public interface ICourseBusiness {

    /**
     * 根据课程的id获取课程章节,
     * 一门课程对应多个课程的章节
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);

}
