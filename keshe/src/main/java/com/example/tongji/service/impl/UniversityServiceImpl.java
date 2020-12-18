package com.example.tongji.service.impl;

import com.example.tongji.domain.University;
import com.example.tongji.mapper.UniversityMapper;
import com.example.tongji.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置 服务层实现
 *
 */
@Service
public class UniversityServiceImpl implements IUniversityService {
    @Autowired
    private UniversityMapper universityMapper;

    @Override
    public List<University> listU(String province) {
        return universityMapper.listU(province);
    }
}
