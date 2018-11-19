package com.online.college.core.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.dao.CourseCommentDao;
import com.online.college.core.course.domain.CourseComment;
import com.online.college.core.course.service.ICourseCommentService;


@Service
public class CourseCommentServiceImpl implements ICourseCommentService {

    @Autowired
    private CourseCommentDao entityDao;

    @Override
    public CourseComment getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<CourseComment> queryAll(CourseComment queryEntity) {
        return entityDao.queryAll(queryEntity);
    }

    /**
     * 1 分页获取
     */
    @Override
    public TailPage<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<CourseComment> items = entityDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    /**
     * 2 查询我的答疑，分页
     */
    @Override
    public TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page) {
        Integer itemsTotalCount = entityDao.getMyQAItemsCount(queryEntity);
        List<CourseComment> items = entityDao.queryMyQAItemsPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    /**
     * 3 创建评论
     */
    @Override
    public void create(CourseComment entity) {
        entityDao.create(entity);
    }

    /**
     * 4 创建
     */
    public void createSelectivity(CourseComment entity) {
        entityDao.createSelectivity(entity);
    }

    /**
     * 5 更新评论
     */
    @Override
    public void update(CourseComment entity) {
        entityDao.update(entity);
    }

    /**
     * 6 有选择的更新
     */
    @Override
    public void updateSelectivity(CourseComment entity) {
        entityDao.updateSelectivity(entity);
    }

    /**
     * 7 删除评论
     */
    @Override
    public void delete(CourseComment entity) {
        entityDao.delete(entity);
    }

    /**
     * 8 逻辑删除评论
     */
    @Override
    public void deleteLogic(CourseComment entity) {
        entityDao.deleteLogic(entity);
    }


}

