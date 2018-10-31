package com.online.college.wechat.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.core.course.domain.CourseSection;
import lombok.Data;

/**
 * 课程章节
 */
@Data
public class CourseSectionVO extends CourseSection {
    private static final long serialVersionUID = 180753077428934254L;

    //小节
    private List<CourseSection> sections = new ArrayList<CourseSection>();


}
