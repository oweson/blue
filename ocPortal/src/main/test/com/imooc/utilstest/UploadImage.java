package com.imooc.utilstest;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.util.CommonUtil;
import org.junit.Test;

import java.io.File;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/8 0008 19:49
 */
public class UploadImage {
    @Test
    public void demo1() {
        /**1 图片的上传*/
        byte[] fileBytes = CommonUtil.getFileBytes(new File("E://yun.jpg"));
        /**key保存在数据库，下次用的时候读取出来*/
        String key = QiniuStorage.uploadImage(fileBytes);
        System.out.println(key);
        String url = QiniuStorage.getUrl(key);
        /**url即使图片的公网访问地址*/
        System.out.println(url);


    }

    @Test
    public void demo2() {
        /** 2 图片的下载,返回图片的地址*/
        String s = "/default/all/0/381a0a67d7104d6faef3b837a9b29ad4.jpeg\n";
        String url = QiniuStorage.getUrl(s);

    }
}
