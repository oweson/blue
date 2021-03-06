package com.online.college.core.course.domain;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import com.online.college.common.util.BeanUtil;

/**
 * 课程查询实体类
 */
@Data
public class CourseQueryDto extends Course {
    /**
     * 需要排序；
     * 需要查询多少条数据
     * 数量
     */

    private static final long serialVersionUID = 6928526481007198051L;

    private String sortField;

    private String sortDirection = "DESC";

    private Integer start = 0;//limit开始

    private Integer count;//数量

    private Integer end;//limit结束


    /**
     * 按照sortField升序
     *
     * @param sortField：指java bean中的属性
     */
    public void ascSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = " ASC ";
        }
    }

    /**
     * 按照sortField降序
     *
     * @param sortField ：指java bean中的属性
     */
    public void descSortField(String sortField) {
        if (StringUtils.isNotEmpty(sortField)) {
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = " DESC ";
        }
    }


    public Integer getEnd() {
        if (null != this.count) {
            if (null == this.start) {
                this.start = 0;
            }
            this.end = this.start + this.count;
        }
        return end;
    }

}
