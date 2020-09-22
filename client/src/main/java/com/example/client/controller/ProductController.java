package com.example.client.controller;

import com.alibaba.fastjson.JSON;
import com.example.client.dao.DepartmentMapper;
import com.example.client.dao.EmployeeMapper;
import com.example.client.domain.Employee;
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
@RequestMapping("product")
public class ProductController {

    @Resource
    private EmployeeMapper employeeMapper;

    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Integer productId){
        Employee employee = employeeMapper.selectByPrimaryKey(productId);

        return JSON.toJSONString(employee);
    }
}
