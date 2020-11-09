package com.jd.local.crawler.service.impl;

import com.example.server.crawler.service.impl.AbstractCrawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * @author pangjunming
 * @Description: 搜索美团城市列表页，并转发请求到对应城市搜索
 * @date 2020/11/5
 */
public class FlowerCityCrawlerMT extends AbstractCrawler {

    public FlowerCityCrawlerMT() {
        this.regex = "http://i.meituan.com/index/changecity/more/[\\w]+";
    }

    @Override
    public void crawl(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//*[@id=\"morecity\"]/div[2]/ul/").nodes();
        Integer count = 0;
        for(Selectable node : nodes){
            String cityPY = node.xpath("/li/a/@data-citypinyin").get();
            String cityCN = node.xpath("/li/a/text()").get();
            page.putField(cityPY, cityCN);
            //指定城市第一页
            page.addTargetRequest("http://i.meituan.com/"+cityPY+"/all/?cid=20387&p=1");
            count++;
        }
        System.out.println("当前url:"+page.getUrl().get()+";获取城市数量:"+count);
    }

    public static void main(String[] args) {
        String url = "http://i.meituan.com/index/changecity/more/A?cevent=imt%2FselectCity%2Fmore";
        String regex = "http://i.meituan.com/index/changecity/more/[\\w]+\\?cevent=imt%2FselectCity%2Fmore";
        System.out.println(url.matches(regex));
    }
}
