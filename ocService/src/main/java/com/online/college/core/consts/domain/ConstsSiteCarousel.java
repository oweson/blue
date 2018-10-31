package com.online.college.core.consts.domain;

import com.online.college.common.orm.BaseEntity;
import lombok.Data;

@Data
public class ConstsSiteCarousel extends BaseEntity{

	private static final long serialVersionUID = -68528406716367757L;

	/**
	*名称
	**/
	private String name;

	/**
	*图片
	**/
	private String picture;

	/**
	*链接
	**/
	private String url;

	/**
	*权重
	**/
	private Integer weight;
	
	/**
	 * 是否可用
	 */
	private Integer enable;
	
	


}

