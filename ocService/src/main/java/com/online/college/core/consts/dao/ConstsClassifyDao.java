package com.online.college.core.consts.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.domain.ConstsCollege;


public interface ConstsClassifyDao {

    /**
     * 1 根据id获取
     **/
    ConstsClassify getById(Long id);

    /**
     * 2 根据code获取
     */
    ConstsCollege getByCode(String code);

    /**
     * 3 获取所有
     **/
    List<ConstsClassify> queryAll();

    /**
     * 4 根据条件动态获取
     *
     * @param queryEntity
     * @return
     */
    List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

    /**
     * 5 获取总数量
     **/
    Integer getTotalItemsCount(ConstsClassify queryEntity);

    /**
     * 6 分页获取
     **/
    List<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page);

    /**
     * 7 创建新记录
     **/
    void create(ConstsClassify entity);

    /**
     * 8 创建新记录
     */
    void createSelectivity(ConstsClassify entity);

    /**
     * 9 根据id更新
     **/
    void update(ConstsClassify entity);

    /**
     * 10 根据id选择性更新自动
     **/
    void updateSelectivity(ConstsClassify entity);

    /**
     * 11 物理删除
     **/
    void delete(ConstsClassify entity);

    /**
     * 12 逻辑删除
     **/
    void deleteLogic(ConstsClassify entity);


}

