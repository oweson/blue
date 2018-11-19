package com.online.college.core.user.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserFollowStudyRecord;
import com.online.college.core.user.domain.UserFollows;


public interface IUserFollowsService {

    /**
     * 1 根据id获取
     **/
    UserFollows getById(Long id);

    /**
     * 2 获取所有
     **/
    List<UserFollows> queryAll(UserFollows queryEntity);

    /**
     * 3 分页获取
     **/
    TailPage<UserFollows> queryPage(UserFollows queryEntity, TailPage<UserFollows> page);

    /**
     * 4 分页获取
     **/
    TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity, TailPage<UserFollowStudyRecord> page);

    /**
     * 5 创建
     **/
    void createSelectivity(UserFollows entity);

    /**
     * 6 根据id更新
     **/
    void update(UserFollows entity);

    /**
     * 7 根据id 进行可选性更新
     **/
    void updateSelectivity(UserFollows entity);

    /**
     * 8 物理删除
     **/
    void delete(UserFollows entity);

    /**
     * 9 逻辑删除
     **/
    void deleteLogic(UserFollows entity);


}

