package com.online.college.core.consts.domain;

import com.online.college.common.orm.BaseEntity;
import lombok.Data;

@Data
public class ConstsClassify extends BaseEntity{
	
	private static final long serialVersionUID = -7695989855010913861L;

	/**
	*名称
	**/
	private String name;
	
	/**
	*编码
	**/
	private String code;

	/**
	*父级别id
	**/
	private String parentCode;

	/**
	*排序
	**/
	private Long sort;





}

