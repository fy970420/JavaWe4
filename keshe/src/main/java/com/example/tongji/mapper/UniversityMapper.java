package com.example.tongji.mapper;

import com.example.tongji.domain.University;

import java.util.List;
/**
 * 参数配置 数据层
 *
 */
public interface UniversityMapper {
    //根据省名词查询大学
    List<University> listU(String province);
}
