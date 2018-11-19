package com.online.college.portal.business;

import java.util.List;
import java.util.Map;

import com.online.college.portal.vo.ConstsClassifyVO;

public interface IPortalBusiness {

    /**
     * 1 获取所有，包括一级分类&二级分类
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 2 获取所有分类
     */
    Map<String, ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 3 为分类设置课程推荐
     */
    void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList);
}
