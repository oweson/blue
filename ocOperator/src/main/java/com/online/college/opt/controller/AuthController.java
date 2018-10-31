package com.online.college.opt.controller;

import javax.servlet.http.HttpServletRequest;

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
 * 用户登录 & 注册
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
        /**已经登录*/
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/login");
    }

    /**
     * 2 登录操作
     */
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String identiryCode, HttpServletRequest request) {

        /**如果已经登录过,
         * 就到首页*/
        if (SessionContext.getAuthUser() != null) {
            return new ModelAndView("redirect:/index.html");
        }

        /**登录操作，验证码判断，输入的和session中存储的是否一致*/
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 1);
            return mv;
        }
        /**验证码通过，进行shiiro的登录操作*/
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            Subject currentUser = SecurityUtils.getSubject();
            /**shiro实现登录*/
            currentUser.login(token);
            /**登录成功跳转到首页*/
            return new ModelAndView("redirect:/index.html");
        } catch (AuthenticationException e) {
            /**登录失败,返回错误码给前端*/
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    /**
     * 2 注册页面
     */
    @RequestMapping(value = "/register")
    public ModelAndView register() {
        //todo
        /**如果用户在登录状态下点击注册；
         * 判断这个注册的账户是否已经存在；
         * 存在的话就直接登录，成功后跳转到首页*/
        if (SessionContext.isLogin()) {
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/register");
    }

    /**
     * 3 实现注册
     */
    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public String doRegister(AuthUser authUser, String identiryCode, HttpServletRequest request) {
        /**验证码判断;
         * request很重要，的参数！！！*/
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }
        /**去数据库进行用户名进行查询，看是否有人使用；
         * 如果使用就返回1 给前端，表示用户名不可用*/
        AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());
        if (tmpUser != null) {
            return JsonView.render(1);
        } else {
            /**用户名不存在，可以使用，
             * 密码加密入库*/
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            authUserService.createSelectivity(authUser);
            /**返回0表示注册成功*/
            return JsonView.render(0);
        }
    }

    /**
     * 4 退出操作
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        /**让session失效，
         * request.getSession().invalidate();
         * */
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }

}
