package com.online.college.core.consts.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsCollege;


public interface IConstsCollegeService {

    /**
     * 1 根据id获取
     **/
    ConstsCollege getById(Long id);

    /**
     * 2 根据code获取
     */
    ConstsCollege getByCode(String code);

    /**
     * 3 获取所有
     **/
    List<ConstsCollege> queryAll(ConstsCollege queryEntity);

    /**
     * 4 分页获取
     **/
    TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page);

    /**
     * 5 创建
     **/
    void create(ConstsCollege entity);

    /**
     * 6 创建网校
     */
    void createSelectivity(ConstsCollege entity);

    /**
     * 7 根据id更新
     **/
    void update(ConstsCollege entity);

    /**
     * 8 根据id 进行可选性更新
     **/
    void updateSelectivity(ConstsCollege entity);

    /**
     * 9 物理删除
     **/
    void delete(ConstsCollege entity);

    /**
     * 10 逻辑删除
     **/
    void deleteLogic(ConstsCollege entity);


}

