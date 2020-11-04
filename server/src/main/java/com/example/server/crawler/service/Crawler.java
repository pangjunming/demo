package com.example.server.crawler.service;

import us.codecraft.webmagic.Page;

/**
 * @author pangjunming
 * @Description: 爬虫类
 * @date 2019/6/13
 */
public interface Crawler {

    public void crawl(Page page);
}