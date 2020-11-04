package com.example.server.crawler.service.impl;


import com.example.server.crawler.service.Crawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pangjunming
 * @Description:
 * @date 2019/6/13
 */
public abstract class AbstractCrawler implements Crawler {

    private static List<AbstractCrawler> crawlers = new ArrayList<>();
    protected static final Logger logger = LoggerFactory.getLogger(AbstractCrawler.class);

    static {
        FlowerCrawler flowerCrawler = new FlowerCrawler();
        FlowerDetailCrawler flowerDetailCrawler = new FlowerDetailCrawler();
        crawlers.add(flowerCrawler);
        crawlers.add(flowerDetailCrawler);

    }

    /**
     * regex 校验规则
     */
    protected String regex = null;

    /**
     * 根据url动态获取爬虫类
     * @param url
     * @return
     */
    public static Crawler validCrawlRegular(String url){
        for(AbstractCrawler crawler : crawlers){
            if(url.matches(crawler.regex)){
                return crawler;
            }
        }
        return null;
    }
}
