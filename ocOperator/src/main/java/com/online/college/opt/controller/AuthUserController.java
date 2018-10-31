package com.online.college.opt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;

@Controller
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private IAuthUserService entityService;

    /**
     * 1 根据id查找一个用户
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        AuthUser user = entityService.getById(id);
        return JsonView.render(user);
    }

    /**
     * 2 分页
     */
    @RequestMapping(value = "/userPageList")
    public ModelAndView queryPage(AuthUser queryEntity, TailPage<AuthUser> page) {
        ModelAndView mv = new ModelAndView("cms/user/userPageList");
        //todo
        mv.addObject("curNav", "user");
        /**判断用户名不为空加入查询条件，
         * 有空个去掉加入查询；
         * 否则设置为null*/
        if (StringUtils.isNotEmpty(queryEntity.getUsername())) {
            queryEntity.setUsername(queryEntity.getUsername().trim());
        } else {
            queryEntity.setUsername(null);
        }
        //todo
        /**state启用和禁用，-1就是不用状态查询，查询全部的信息；*/
        if (Integer.valueOf(-1).equals(queryEntity.getStatus())) {
            queryEntity.setStatus(null);
        }

        page = entityService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        /**把搜索的值记录下来知道我搜索的是什么东西；
         * 搜索框的会显示*/
        mv.addObject("queryEntity", queryEntity);

        return mv;
    }

    /**
     * 3 merge融入混合相融；
     * 更新操作
     */
    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(AuthUser entity) {
        /**这两个字段不允许更新，*/
        entity.setUsername(null);//不更新
        entity.setRealname(null);//不更新
        /**入库，有的字段不变；
         * 0 表示更新成功*/
        entityService.updateSelectivity(entity);
        return new JsonView(0).toString();
    }

}

