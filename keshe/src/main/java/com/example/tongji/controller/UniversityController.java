package com.example.tongji.controller;

import com.example.tongji.domain.University;
import com.example.tongji.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UniversityController {
    @Autowired
    private IUniversityService universityService;

    @GetMapping("/search/{prvince}") //请求路径 如：http://localhost/serar/湖北
    @ResponseBody
    public List<University> searchList(@PathVariable("prvince")String province){
        return universityService.listU(province);
    }

}
