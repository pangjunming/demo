package com.example.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.server.crawler.CrawlerMain;
import com.example.server.crawler.pipeline.FlowerPipeLine;
import com.example.server.dao.DepartmentMapper;
import com.example.server.domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

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

    @Resource
    private FlowerPipeLine flowerPipeLine;

    @RequestMapping("crawler")
    @ResponseBody
    public String crawler(String url, String city){
        flowerPipeLine.setCity(city);
        Spider.create(new CrawlerMain())
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                .addPipeline(flowerPipeLine)
                .thread(10)
                //启动爬虫
                .run();
        return "success";
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("aaaaa");
        logger.debug("说点{}呢","什么");
        logger.info("说点{}呢","什么");
        logger.error("说点{}呢","什么");
    }

}
