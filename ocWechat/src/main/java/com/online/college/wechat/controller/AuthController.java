package com.online.college.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.wechat.wxapi.process.WxMemoryCacheClient;


/**
 * 用户登录
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 1 登录页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
		/*if(SessionContext.isWxLogin()){
			return new ModelAndView("redirect:/index.html");
		}*/
        /**跳转登录页面*/
        return new ModelAndView("login");
    }

    /**
     * 2 实现登录
     */
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String toUrl, HttpServletRequest request) {
        //如果已经登录过
		/*if(SessionContext.getWxAuthUser(request) != null){
			return new ModelAndView("redirect:/index.html");
		}*/

        //判断用户名密码是否正确
        AuthUser tmpAuthUser = new AuthUser();
        tmpAuthUser.setUsername(user.getUsername());
        tmpAuthUser.setPassword(EncryptUtil.encodedByMD5(user.getPassword()));
        /**查库*/
        tmpAuthUser = authUserService.getByUsernameAndPassword(tmpAuthUser);
        if (null != tmpAuthUser) {//登录成功
            /**关注的时候记录登录之前openId已经得到，放在了WxMemoryCache*/

            /**获取当前用户openid，必须关注才可以登录*/
            String openid = WxMemoryCacheClient.getOpenid(request.getSession().getId());

            if (StringUtils.isNotEmpty(openid)) {
                tmpAuthUser.setOpenId(openid);
                /**设置openid，并更新数据库，唯一的，类似微信号；*/
                authUserService.updateSelectivity(tmpAuthUser);
            } else {
                //todo 必须关注才可以微信登录
                /**跳转到一个必须关注提示的页面*/
            }
//request.getSession().getId();
            //模拟session存储数据
            /**拿到sesssion得到id */
            String sessionId = request.getSession().getId();
            //todo
            tmpAuthUser.setHeader(QiniuStorage.getUrl(tmpAuthUser.getHeader()));
            /**设置到当前的回话中*/
            SessionContext.setAttribute(request, sessionId, tmpAuthUser);
            return new ModelAndView("redirect:/user/index.html");
        }

        //登录失败
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errcode", 2);
        return mv;
    }

}
