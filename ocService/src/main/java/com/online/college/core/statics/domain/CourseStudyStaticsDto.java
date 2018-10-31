package com.online.college.core.statics.domain;

import lombok.Data;

import java.util.Date;

/**
 * 课程学习统计
 */
@Data
public class CourseStudyStaticsDto {

    private Integer totalCount;

    private String dateStr;

    private Date startDate;

    private Date endDate;

    private Long courseId;


}
