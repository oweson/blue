package com.online.college.core.consts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.dao.ConstsCollegeDao;
import com.online.college.core.consts.domain.ConstsCollege;
import com.online.college.core.consts.service.IConstsCollegeService;


@Service
public class ConstsCollegeServiceImpl implements IConstsCollegeService {

    @Autowired
    private ConstsCollegeDao entityDao;

    /**
     * 1 根据id获取
     */
    public ConstsCollege getById(Long id) {
        return entityDao.getById(id);
    }

    /**
     * 2 根据code获取
     */
    public ConstsCollege getByCode(String code) {
        return entityDao.getByCode(code);
    }

    /**
     * 3 条件查询，不分页
     */
    public List<ConstsCollege> queryAll(ConstsCollege queryEntity) {
        return entityDao.queryAll(queryEntity);
    }

    /**
     * 4 查询全部，分页
     */
    public TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page) {
        /**查总的数量，不算已逻机删除的*/
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);

        List<ConstsCollege> items = entityDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    /**
     * 5 创建
     **/
    public void create(ConstsCollege entity) {
        entityDao.create(entity);
    }

    /**
     * 6 创建网校
     */
    public void createSelectivity(ConstsCollege entity) {
        this.entityDao.createSelectivity(entity);
    }

    /**
     * 7 更新
     */
    public void update(ConstsCollege entity) {
        entityDao.update(entity);
    }

    /**
     * 8 有选择的更新
     */
    public void updateSelectivity(ConstsCollege entity) {
        entityDao.updateSelectivity(entity);
    }

    /**
     * 9 删除
     */
    public void delete(ConstsCollege entity) {
        entityDao.delete(entity);
    }

    /**
     * 10 逻辑删除
     */
    public void deleteLogic(ConstsCollege entity) {
        entityDao.deleteLogic(entity);
    }


}

