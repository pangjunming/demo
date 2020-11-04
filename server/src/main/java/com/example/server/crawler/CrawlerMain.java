package com.example.server.crawler;

import com.example.server.crawler.service.Crawler;
import com.example.server.crawler.service.impl.AbstractCrawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/28
 */
public class CrawlerMain implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).addCookie("i.meituan.com","JSESSIONID","9grtsq7q0uyl168vw32vveo3k").addCookie("meituan.com","latlng","39.999023,116.381157,1601262803010").setUserAgent("PostmanRuntime/7.26.5");

    @Override
    public void process(Page page) {
        Crawler crawler = AbstractCrawler.validCrawlRegular(page.getUrl().get());
        if(crawler == null){
            page.setSkip(true);
        }else {
            crawler.crawl(page);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
