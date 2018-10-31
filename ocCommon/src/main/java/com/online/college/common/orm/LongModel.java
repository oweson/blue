package com.online.college.common.orm;

import lombok.Data;

import java.io.Serializable;

@Data
public class LongModel implements Identifier<Long>, Serializable {
    private static final long serialVersionUID = 7978917143723588623L;

    private Long id;


}
