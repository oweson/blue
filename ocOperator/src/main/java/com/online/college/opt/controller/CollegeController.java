package com.online.college.opt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsCollege;
import com.online.college.core.consts.service.IConstsCollegeService;

/**
 * 网校管理，大学管理
 */
@Controller
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private IConstsCollegeService entityService;

    /**
     * 1 分页，15条
     *
     * @param queryEntity
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryPageList")
    public ModelAndView queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page) {
        /** 设置视图*/

        ModelAndView mv = new ModelAndView("cms/college/collegePageList");
        mv.addObject("curNav", "college");
        /**搜索，名字是唯一的条件，不为空加入查询条件*/

        if (StringUtils.isNotEmpty(queryEntity.getName())) {
            queryEntity.setName(queryEntity.getName().trim());
        } else {
            queryEntity.setName(null);
        }
        /**查询分页信息，放入request*/
        page = entityService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);
        return mv;
    }

    /**
     * 3 根据id进行查找，修改信息之前的回显示
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        return JsonView.render(entityService.getById(id));
    }

    /**
     * 4 添加 大学的编码是唯一的
     * id为null创建；
     * 不为null就是更新
     */
    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(ConstsCollege entity) {
        if (entity.getId() == null) {
            ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
            if (tmpEntity != null) {
                return JsonView.render(1, "此编码已存在");
            }
            entityService.createSelectivity(entity);
        } else {
            ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
            if (tmpEntity != null && !tmpEntity.getId().equals(entity.getId())) {
                return JsonView.render(1, "此编码已存在");
            }
            entityService.updateSelectivity(entity);
        }
        return new JsonView().toString();
    }

    /**
     * 5 逻辑删除
     */
    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(ConstsCollege entity) {
        entityService.deleteLogic(entity);
        return new JsonView().toString();
    }

}

