package com.example.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.server.dao.DepartmentMapper;
import com.example.server.domain.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(String name){
        return "hello， " + name;
    }


    /**
     * departmentMapper
     */
    @Resource
    private DepartmentMapper departmentMapper;

    @RequestMapping("queryDeptById")
    @ResponseBody
    public String queryDeptById(Integer id){
        Department department = departmentMapper.selectByPrimaryKey(id);
        return JSONObject.toJSONString(department);
    }
}
