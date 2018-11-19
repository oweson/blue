package com.online.college.portal.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;

/**
 * 首页业务层
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Autowired
    private ICourseService courseService;

    /**
     * 1 获取所有，包括一级分类&二级分类,二级分类已经赊账到了一级分类上面
     */
    public List<ConstsClassifyVO> queryAllClassify() {
        List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();
        /**values() 方法用于返回此映射中包含的值的Collection视图。*/
        for (ConstsClassifyVO vo : this.queryAllClassifyMap().values()) {
            /**一级分类存放*/
            resultList.add(vo);
        }
        return resultList;
    }

    /**
     * 2 获取所有分类
     */
    public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
        Map<String, ConstsClassifyVO> resultMap = new LinkedHashMap<String, ConstsClassifyVO>();
        Iterator<ConstsClassify> it = constsClassifyService.queryAll().iterator();
        while (it.hasNext()) {
            ConstsClassify c = it.next();
            if ("0".equals(c.getParentCode())) {
                /**id为0一级分类*/
                ConstsClassifyVO vo = new ConstsClassifyVO();
                BeanUtils.copyProperties(c, vo);
                /**k对应二级分类的parentcode*/
                resultMap.put(vo.getCode(), vo);
            } else {
                /**二级分类*/
                if (null != resultMap.get(c.getParentCode())) {
                    /**添加到子分类中,二级分类；
                     * 下面首先得对像.列表*/
                    resultMap.get(c.getParentCode()).getSubClassifyList().add(c);
                }
            }
        }
        return resultMap;
    }

    /**
     * 3 为分类设置课程推荐
     */
    public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList) {
        if (CollectionUtils.isNotEmpty(classifyVoList)) {
            for (ConstsClassifyVO item : classifyVoList) {
                CourseQueryDto queryEntity = new CourseQueryDto();
                queryEntity.setCount(5);
                queryEntity.descSortField("weight");
                /**分类code*/
                queryEntity.setClassify(item.getCode());

                List<Course> tmpList = this.courseService.queryList(queryEntity);
                if (CollectionUtils.isNotEmpty(tmpList)) {
                    /*设置推建列表*/
                    item.setRecomdCourseList(tmpList);
                }
            }
        }
    }

}
