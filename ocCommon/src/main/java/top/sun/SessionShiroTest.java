package top.sun;

import com.online.college.common.web.auth.SessionUser;
import org.apache.shiro.SecurityUtils;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/24 0024 21:31
 */
public class SessionShiroTest {
    public static void main(String[] args) {
        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getPrincipal();
    }
}
