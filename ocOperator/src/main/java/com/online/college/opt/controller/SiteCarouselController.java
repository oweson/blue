package com.online.college.opt.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;

/**
 * 轮播配置
 */
@Controller
@RequestMapping("/carousel")
public class SiteCarouselController {

    @Autowired
    private IConstsSiteCarouselService entityService;

    /**
     * 1 分页
     */
    @RequestMapping(value = "/queryPage")
    public ModelAndView queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page) {
        ModelAndView mv = new ModelAndView("cms/carousel/pagelist");
        mv.addObject("curNav", "carousel");
        page.setPageSize(5);
        /**每页5条数据*/
        page = entityService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);
        return mv;
    }

    /**
     * 2 更新信息的回显示；
     * 新的添加id为null,修改的时候id是要有的信息要回显；
     * 让用户用户知道修改之前的数据是什么
     */
    @RequestMapping(value = "/toMerge")
    public ModelAndView toMerge(ConstsSiteCarousel entity) {
        ModelAndView mv = new ModelAndView("cms/carousel/merge");
        mv.addObject("curNav", "carousel");
         /**修改信息要传入的。id不能为因为要知道修改哪一个！！！*/
        if (entity.getId() != null) {
            entity = entityService.getById(entity.getId());
            if (null != entity && StringUtils.isNotEmpty(entity.getPicture())) {
                String pictureUrl = QiniuStorage.getUrl(entity.getPicture(), ThumbModel.THUMB_128);
                /**url要回显*/
                entity.setPicture(pictureUrl);
            }
        }
        mv.addObject("entity", entity);
        return mv;
    }

    /**
     * 3 处理更新的操作
     */
    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(ConstsSiteCarousel entity, @RequestParam MultipartFile pictureImg) {
        String key = null;
        try {
            /**判断图片的字节流大于0，说明图片有内容*/
            if (null != pictureImg && pictureImg.getBytes().length > 0) {
                key = QiniuStorage.uploadImage(pictureImg.getBytes());
                /**新的key,防止盗图，key在数据库，服务接口才可以*/
                entity.setPicture(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (entity.getId() == null) {
            /**新建*/
            entityService.createSelectivity(entity);
        } else {
            /**更新*/
            entityService.updateSelectivity(entity);
        }
        return new ModelAndView("redirect:/carousel/queryPage.html");
    }

    /**
     * 4 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(ConstsSiteCarousel entity) {
        entityService.delete(entity);
        return new JsonView().toString();
    }

}

