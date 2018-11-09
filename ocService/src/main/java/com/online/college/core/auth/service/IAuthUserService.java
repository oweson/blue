package com.online.college.core.auth.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.auth.domain.AuthUser;


public interface IAuthUserService {

    /**
     * 1 根据username获取
     **/
    AuthUser getByUsername(String username);

    /**
     * 2 创建
     **/
    void createSelectivity(AuthUser entity);


    /**
     * 3 根据id获取
     **/
    AuthUser getById(Long id);

    /**
     * 4 根据username和password获取
     **/
    AuthUser getByUsernameAndPassword(AuthUser authUser);

    /**
     * 5 获取首页推荐5个讲师
     **/
    List<AuthUser> queryRecomd();

    /**
     * 6 分页获取，第一个参数搜索的入参，第二个分页的信息
     **/
    TailPage<AuthUser> queryPage(AuthUser queryEntity, TailPage<AuthUser> page);

    /**
     * 7 根据id更新
     **/
    void update(AuthUser entity);

    /**
     * 8 根据id 进行可选性更新
     **/
    void updateSelectivity(AuthUser entity);

    /**
     * 9 物理删除
     **/
    void delete(AuthUser entity);

    /**
     * 10 逻辑删除
     **/
    void deleteLogic(AuthUser entity);


}

