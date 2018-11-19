package com.online.college.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserMessage;


public interface IUserMessageService {

    /**
     * 1 根据id获取
     **/
    UserMessage getById(Long id);

    /**
     * 2 获取所有
     **/
    List<UserMessage> queryAll(UserMessage queryEntity);

    /**
     * 3 分页获取
     **/
    TailPage<UserMessage> queryPage(UserMessage queryEntity, TailPage<UserMessage> page);

    /**
     * 4 创建
     **/
    void create(UserMessage entity);

    /**
     * 5 根据id更新
     **/
    void update(UserMessage entity);

    /**
     * 6 根据id 进行可选性更新
     **/
    void updateSelectivity(UserMessage entity);

    /**
     * 7 物理删除
     **/
    void delete(UserMessage entity);

    /**
     * 8逻辑删除
     **/
    void deleteLogic(UserMessage entity);


}

