package com.online.college.core.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * 关注的用户学习记录dto
 */
@Data
public class UserFollowStudyRecord {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 章节id
     */
    private Long sectionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户头像
     */
    private String header;

    /**
     * 关注用户id
     */
    private Long followId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 章节名称
     */
    private String sectionName;

    /**
     * 创建时间
     */
    private Date createTime;


}
