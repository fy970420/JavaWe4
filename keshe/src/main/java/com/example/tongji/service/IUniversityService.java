package com.example.tongji.service;

import com.example.tongji.domain.University;

import java.util.List;
/**
 * 参数配置 服务层
 *
 */
public interface IUniversityService {
    List<University> listU(String province);
}
