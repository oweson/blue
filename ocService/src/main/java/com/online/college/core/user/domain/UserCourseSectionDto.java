package com.online.college.core.user.domain;

import lombok.Data;

/**
 * 用户学习课程dto
 */
@Data
public class UserCourseSectionDto extends UserCourseSection {

    private static final long serialVersionUID = 608405844566660424L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 章节名
     */
    private String sectionName;

    /**
     * 用户头像
     */
    private String header;


}
