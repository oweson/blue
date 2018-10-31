package com.online.college.core.auth.dao; /**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/6 0006 11:19
 */

/**
 * todu....单元测试
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class BaseClass {
}
