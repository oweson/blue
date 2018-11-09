package top.sea521;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/9 0009 20:42
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BaseClass {


}
