package com.online.college.core.course.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.consts.CourseEnum;
import com.online.college.core.course.dao.CourseDao;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseDao entityDao;

    /**
     * 有图片不为空就设置到课程上面
     */
    private void prepareCoursePicture(Course course) {
        /**课程不为空并且有图片及取出七牛云的图片设置到课程*/
        if (null != course && StringUtils.isNotEmpty(course.getPicture())) {
            course.setPicture(QiniuStorage.getUrl(course.getPicture()));
        }
    }

    @Override
    public Course getById(Long id) {
        Course course = entityDao.getById(id);
        prepareCoursePicture(course);
        return course;
    }

    @Override
    public List<Course> queryList(CourseQueryDto queryEntity) {
        /**是否上架*/
        if (null == queryEntity.getOnsale()) {
            queryEntity.setOnsale(CourseEnum.ONSALE.value());
        }
        return entityDao.queryList(queryEntity);
    }

    @Override
    public TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page) {
        /**获得课程的总数*/
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        /**所有的课程*/
        List<Course> items = entityDao.queryPage(queryEntity, page);
        if (CollectionUtils.isNotEmpty(items)) {
            for (Course item : items) {
                /**如果课程有图片就进行七牛云的处理，设置图片到课程进行前端的展示*/
                prepareCoursePicture(item);
            }
        }
        /**为分页设置总数*/
        page.setItemsTotalCount(itemsTotalCount);
        /**为分页设置数据*/
        page.setItems(items);
        return page;
    }

    @Override
    public void createSelectivity(Course entity) {
        entityDao.createSelectivity(entity);
    }

    @Override
    public void updateSelectivity(Course entity) {
        entityDao.updateSelectivity(entity);
    }

    //物理删除
    @Override
    public void delete(Course entity) {
        entityDao.delete(entity);
    }

    //逻辑删除
    @Override
    public void deleteLogic(Course entity) {
        entityDao.deleteLogic(entity);
    }

}


