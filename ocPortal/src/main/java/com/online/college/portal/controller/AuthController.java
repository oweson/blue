package com.online.college.portal.controller;

import javax.servlet.http.HttpServletRequest;

import com.qiniu.util.Auth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;


/**
 * 1 用户登录 & 注册
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    /**
     * 1 注册页面
     */
    @RequestMapping(value = "/register")
    public ModelAndView register() {
        if (SessionContext.isLogin()) {
            /**shiro校验是否登录*/
            return new ModelAndView("redirect:/index.html");
        }
        /**跳转到注册的页面*/
        return new ModelAndView("auth/register");
    }

    @RequestMapping(value = "/myregister")
    public ModelAndView myRegister() {
        return new ModelAndView("auth/register");
    }

    /**
     * 2 实现注册
     */
    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public String doRegister(AuthUser authUser, String identiryCode, HttpServletRequest request) {
        /**验证码判断*/
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            /**有验证码但是和sesson里面的不一样，错误码*/
            return JsonView.render(2);
        }

        AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());
        if (tmpUser != null) {
            /**用户名存在，不能注册了，错误码提示*/
            return JsonView.render(1);
        } else {
            /**用户名不存在可以注册加密后入库*/
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserService.createSelectivity(authUser);
            return JsonView.render(0);
        }
    }

    public String register(AuthUser authUser, String code, HttpServletRequest request) {
        if (code != null && code.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }
//判断验证码
        AuthUser byUsername = authUserService.getByUsername(authUser.getNickname());
        if (byUsername != null) {
            return JsonView.render(1);
        } else {
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserService.createSelectivity(authUser);
            return JsonView.render(0);
        }

    }

    /**
     * 3 登录页面，登录了就跳转到首页
     */
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/login");
    }

    /**
     * 4 ajax登录
     */
    @RequestMapping(value = "/ajaxlogin")
    @ResponseBody
    public String ajaxLogin(AuthUser user, String identiryCode, Integer rememberMe, HttpServletRequest request) {
        /**验证码判断*/
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2, "验证码不正确！");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            if (rememberMe != null && rememberMe == 1) {
                token.setRememberMe(true);
            }
            currentUser.login(token);//shiro：不抛出异常，登陆成功
            return new JsonView().toString();
        } catch (AuthenticationException e) { //登录失败
            return JsonView.render(1, "用户名或密码不正确");
        }
    }

    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String identiryCode, HttpServletRequest request) {

        //如果已经登录过
        if (SessionContext.getAuthUser() != null) {
            return new ModelAndView("redirect:/user/home.html");
        }

        //验证码判断
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 1);
            return mv;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);//shiro实现登录
            return new ModelAndView("redirect:/user/home.html");
        } catch (AuthenticationException e) { //登录失败
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    /**
     * 4 登出，调用shiro的logout方法，重定向到首页
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }

}
