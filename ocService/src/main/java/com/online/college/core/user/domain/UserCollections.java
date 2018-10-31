package com.online.college.core.user.domain;

import com.online.college.common.orm.BaseEntity;
import lombok.Data;

@Data
public class UserCollections extends BaseEntity{

	private static final long serialVersionUID = -3909997252117758595L;

	/**
	*用户id
	**/
	private Long userId;

	/**
	*用户收藏分类
	**/
	private Integer classify;
	
	/**
	 * 用户收藏id
	 */
	private Long objectId;

	/**
	*用户收藏备注
	**/
	private String tips;
	
	/**
	 * 收藏名称
	 */
	private String name;


	
}

