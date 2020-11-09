package com.example.server.crawler.service.impl;

import com.example.server.domain.FlowerVender;
import us.codecraft.webmagic.Page;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/28
 */
public class FlowerDetailCrawler extends AbstractCrawler {

    public FlowerDetailCrawler() {
        this.regex = "http://i.meituan.com/poi/[\\d]+";
    }

    @Override
    public void crawl(Page page) {
        try {
            String title = page.getHtml().xpath("//*[@id=\"deal-list\"]/dl[1]/dd/dl/dd[1]/div/h1/text()").get();
            String address = page.getHtml().xpath("//*[@id=\"deal-list\"]/dl[1]/dd/dl/dd[2]/div/h6/a/div/text()").get();
            String phone = page.getHtml().xpath("//*[@id=\"deal-list\"]/dl[1]/dd/dl/dd[2]/div/p/a/@data-tele").get();
            String url = page.getUrl().get();
            String venderId = url.replaceAll("http://i.meituan.com/poi/", "");
            FlowerVender flowerVenderEntity = new FlowerVender();
            flowerVenderEntity.setAddess(address);
            flowerVenderEntity.setPhone(phone);
            flowerVenderEntity.setVenderId(venderId);
            flowerVenderEntity.setVenderName(title);
            flowerVenderEntity.setSource("meituan");
            if(page.getResultItems().get("city")!=null){
                flowerVenderEntity.setCity((String)page.getResultItems().get("city"));
            }
            page.putField("vender",flowerVenderEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
