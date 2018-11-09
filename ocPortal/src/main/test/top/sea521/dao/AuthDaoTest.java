package top.sea521.dao;

import com.online.college.core.auth.dao.AuthUserDao;
import com.online.college.core.auth.domain.AuthUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.sea521.BaseClass;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/9 0009 20:47
 */
public class AuthDaoTest extends BaseClass {
    @Autowired
    private AuthUserDao authUserDao;
    @Test
    public  void demo1(){
        AuthUser admin = authUserDao.getByUsername("111111");
        Assert.assertNotNull(admin);

    }
}
