package com.online.college.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserCollections;


public interface IUserCollectionsService {

    /**
     * 1 根据id获取
     **/
    UserCollections getById(Long id);

    /**
     * 2 获取所有
     **/
    List<UserCollections> queryAll(UserCollections queryEntity);

    /**
     * 3 分页获取
     **/
    TailPage<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page);

    /**
     * 4 创建
     **/
    void createSelectivity(UserCollections entity);

    /**
     * 5 根据id更新
     **/
    void update(UserCollections entity);

    /**
     * 6 根据id 进行可选性更新
     **/
    void updateSelectivity(UserCollections entity);

    /**
     * 7 物理删除
     **/
    void delete(UserCollections entity);

    /**
     * 8 逻辑删除
     **/
    void deleteLogic(UserCollections entity);


}

