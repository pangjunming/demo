package com.example.server.crawler.service.impl;

import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/27
 */
public class FlowerCrawler extends AbstractCrawler{


    public FlowerCrawler() {
        this.regex = "http://i.meituan.com/[\\w]+/all/\\?cid=20387&p=[\\d]+";
    }

    @Override
    public void crawl(Page page) {
        try {
            //列表页中获取店铺详情页跳转链接并去重
            List<String> venderDetailUrls = page.getHtml().regex("i.meituan.com/poi/[\\d]+").all();
            List<String> collect = venderDetailUrls.stream().map(s -> "http://" + s).distinct().collect(Collectors.toList());
            List<String> nextUrls = page.getHtml().css(".pager").links().all();
            //识别列表页下一页链接，过滤上一页链接，放入目标url
            String url = page.getUrl().get();
            Integer currentPage = Integer.valueOf(url.replaceAll("http://i.meituan.com/[\\w]+/all/\\?cid=20387&p=", ""));
            for(String nextUrl:nextUrls){
                if(StringUtils.isEmpty(nextUrl)){
                    continue;
                }
                //解析并判断页码是否是下一页
                Integer integer = Integer.valueOf(nextUrl.replaceAll("http://i.meituan.com/[\\w]+/all/\\?p=", ""));
                if(integer > currentPage){
                    System.out.println("设置下一页: "+ nextUrl);
                    String s = url.replaceAll("p=[\\d]+", "p=" + integer);
                    page.addTargetRequests(Arrays.asList(s));
                }
            }
            //将店铺详情页放入目标url
            if(!CollectionUtils.isEmpty(collect)){
                page.addTargetRequests(collect);
            }
            logger.error("鲜花商家爬取，来源：美团，第{}页完成", currentPage );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String s = "http://i.meituan.com/beijing/all/?cid=20387&p=67&stid_b=3&cateType=poi";
        String regex = "http://i.meituan.com/[\\w]+/all/\\?cid=20387&p=[\\d]+&stid_b=3&cateType=poi";
        System.out.println(s.matches(regex));
    }
}
