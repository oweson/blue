package com.online.college.opt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.opt.business.IPortalBusiness;
import com.online.college.opt.vo.ConstsClassifyVO;

/**
 * 课程分类管理
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private IConstsClassifyService entityService;

    @Autowired
    private IPortalBusiness portalBusiness;

    /**
     * 1 根据分类的id查看二级分类的信息
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        return JsonView.render(entityService.getById(id));
    }

    /**
     * 2 首页，一级分类和二级分类
     */
    @RequestMapping(value = "/index")
    public ModelAndView classifyIndex(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
        ModelAndView mv = new ModelAndView("cms/classify/classifyIndex");
        /**curNav告诉系统是哪一个的分类，点击设置灰色效果*/
        mv.addObject("curNav", "classify");
        Map<String, ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();

        /**所有一级分类*/
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
        for (ConstsClassifyVO vo : classifyMap.values()) {
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);
        //todo 一级 二级分类

        List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
        for (ConstsClassifyVO vo : classifyMap.values()) {
            /**全部放进来*/
            subClassifys.addAll(vo.getSubClassifyList());
        }
        mv.addObject("subClassifys", subClassifys);//所有二级分类

        return mv;
    }

    /**
     * 3 分类信息的修改
     */
    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(ConstsClassify entity) {
        if (entity.getId() == null) {
            ConstsClassify tmpEntity = entityService.getByCode(entity.getCode());
            if (tmpEntity != null) {
                return JsonView.render(1, "此编码已存在");
            }
            entityService.createSelectivity(entity);
        } else {
            entityService.updateSelectivity(entity);
        }
        return new JsonView().toString();
    }

    /**
     * 4 逻辑删除
     */
    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(ConstsClassify entity) {
        entityService.deleteLogic(entity);
        return new JsonView().toString();
    }


}

