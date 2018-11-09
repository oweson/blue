package top.sea521;

import com.online.college.common.web.SpringBeanFactory;
import com.online.college.test.dao.TestDao;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/8 0008 11:05
 */
/**testCase是spring的继承就好了*/
public class AppTest extends TestCase {
    @Test
    public void testDb(){
        TestDao testDao = (TestDao) SpringBeanFactory.getBean("testDao");
        Map<String, Object> map = testDao.testQuery();
        System.out.println(map.get("curdate"));

    }

}
