package com.online.college.core.user.domain;

import com.online.college.common.orm.BaseEntity;
import lombok.Data;

@Data
public class UserFollows extends BaseEntity {

    private static final long serialVersionUID = -275116124638094439L;

    /**
     * 用户id
     **/
    private Long userId;

    /**
     * 关注的用户id
     **/
    private Long followId;


}

