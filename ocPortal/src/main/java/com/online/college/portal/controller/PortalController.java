package com.online.college.portal.controller;

import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.consts.CourseEnum;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 网站主页
 */
@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private IConstsSiteCarouselService siteCarouselService;

    @Autowired
    private ICourseService courseService;
    /**
     * 根据名字进行加载注入；
     * 其他的有根据类型注入
     */
    @Autowired
    private IAuthUserService authUserService;


    /**
     * 1 首页，web.xml配置了欢迎的页面，访问项目等于访问了index.html
     * s.base=oc-portal引用了
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");

        /** 2 加载轮播,四张图片轮播*/
        List<ConstsSiteCarousel> carouselList = siteCarouselService.queryCarousels(4);
        mv.addObject("carouselList", carouselList);

        /** 3 课程分类(一级分类）*/
        List<ConstsClassifyVO> classifys = portalBusiness.queryAllClassify();

        /** 4课程推荐*/
        portalBusiness.prepareRecomdCourses(classifys);
        mv.addObject("classifys", classifys);

        //todo 所有的查询都是queryList知识入参数不一样

        //todo index.html是背景色显示，可以url图图片填充
        /** 5 获取5门实战课推荐，根据权重（weight）进行排序*/
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.setCount(5);//5门
        queryEntity.setFree(CourseEnum.FREE_NOT.value());//非免费的：实战课
        queryEntity.descSortField("weight");//按照weight降序排列
        List<Course> actionCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("actionCourseList", actionCourseList);

        /** 6获取5门免费课推荐，根据权重（weight）进行排序；
         * 这里复用了查询对象queryEntity*/
        queryEntity.setFree(CourseEnum.FREE.value());//非免费的：实战课
        List<Course> freeCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("freeCourseList", freeCourseList);

        /** 7 获取7门java课程，根据权重（学习数量studyCount）进行降序排序*/
        queryEntity.setCount(7);
        /**不分实战和免费类别，这个字段不参条件的拼装，全部查询*/
        queryEntity.setFree(null);
        queryEntity.setSubClassify("java");//java分类
        queryEntity.descSortField("studyCount");//按照studyCount降序排列，这是个方法两个作用字段
        List<Course> javaCourseList = this.courseService.queryList(queryEntity);
        mv.addObject("javaCourseList", javaCourseList);

        /** 8 加载讲师*/
        List<AuthUser> recomdTeacherList = authUserService.queryRecomd();
        mv.addObject("recomdTeacherList", recomdTeacherList);

        return mv;
    }
}

