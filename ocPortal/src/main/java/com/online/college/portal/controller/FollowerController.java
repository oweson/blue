package com.online.college.portal.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.user.domain.UserFollows;
import com.online.college.core.user.service.IUserFollowsService;

/**
 * 用户关注
 */
@Controller
@RequestMapping("follow")
public class FollowerController {

    @Autowired
    private IUserFollowsService userFollowsService;

    /**
     * 1 收藏
     */
    @RequestMapping(value = "/doFollow")
    @ResponseBody
    public String doFollow(Long followId) {
        //获取当前用户
        Long curUserId = SessionContext.getUserId();
        UserFollows userFollows = new UserFollows();

        userFollows.setUserId(curUserId);
        userFollows.setFollowId(followId);
        List<UserFollows> list = userFollowsService.queryAll(userFollows);
        /**收藏后的再次点击就是取消*/
        if (CollectionUtils.isNotEmpty(list)) {
            userFollowsService.delete(list.get(0));
            return new JsonView(0).toString();
        } else {
            userFollows.setCreateTime(new Date());
            userFollowsService.createSelectivity(userFollows);
            /**已经关注*/
            return new JsonView(1).toString();
        }
    }

    /**
     * 2 是否已经关注
     */
    @RequestMapping(value = "/isFollow")
    @ResponseBody
    public String isFollow(Long followId) {
        //获取当前用户
        Long curUserId = SessionContext.getUserId();
        UserFollows userFollows = new UserFollows();

        userFollows.setUserId(curUserId);
        userFollows.setFollowId(followId);
        List<UserFollows> list = userFollowsService.queryAll(userFollows);
        /**已经关注*/
        if (CollectionUtils.isNotEmpty(list)) {
            return new JsonView(1).toString();
        } else {
            return new JsonView(0).toString();
        }
    }

}


