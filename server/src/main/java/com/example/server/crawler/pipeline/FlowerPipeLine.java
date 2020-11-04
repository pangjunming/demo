package com.example.server.crawler.pipeline;

import com.example.server.dao.FlowerVenderMapper;
import com.example.server.domain.FlowerVender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/28
 */
@Component
public class FlowerPipeLine implements Pipeline {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowerPipeLine.class);

    @Resource
    private FlowerVenderMapper flowerVenderMapper;

    /**
     * city
     */
    private String city;

    @Override
    public void process(ResultItems resultItems, Task task) {
        FlowerVender vender = (FlowerVender) resultItems.get("vender");
        if(vender == null){
            return;
        }
        try {
            vender.setCity(city);
            flowerVenderMapper.insertSelective(vender);
        } catch (Exception e) {
            LOGGER.error("插入数据异常，数据:{}", vender, e);
        }
    }

    public void setCity(String city){
        this.city = city;
    }
}
