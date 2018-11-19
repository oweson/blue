package com.online.college.core.consts.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsClassify;


public interface IConstsClassifyService {

    /**
     * 1 根据id获取
     **/
    ConstsClassify getById(Long id);

    /**
     * 2 获取所有
     **/
    List<ConstsClassify> queryAll();

    /**
     * 3 根据code获取
     */
    ConstsClassify getByCode(String code);

    /**
     * 4 根据条件动态获取
     **/
    List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

    /**
     * 5 分页获取
     **/
    TailPage<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page);

    /**
     * 6 创建
     **/
    void create(ConstsClassify entity);

    /**
     * 7 创建
     */
    void createSelectivity(ConstsClassify entity);

    /**
     * 8 根据id 进行可选性更新
     **/
    void updateSelectivity(ConstsClassify entity);

    /**
     * 9 物理删除
     **/
    void delete(ConstsClassify entity);

    /**
     * 10 逻辑删除
     **/
    void deleteLogic(ConstsClassify entity);


}

