package com.example.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.server.domain.Employee;
import com.example.server.hystrix.command.GetProductInfoCommand;
import com.example.server.hystrix.command.GetProductInfosCommand;
import com.example.server.util.HttpClientUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

import java.util.List;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */
@Controller
@RequestMapping("/cache")
public class CacheController {


    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Integer productId){
        String url = "http://127.0.0.1:8081/product/getProductInfo?productId=" + productId;
        try {
            String response = HttpClientUtil.sendGetRequest(url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Integer productId){
        HystrixCommand<Employee> getProductInfoCommand = new GetProductInfoCommand(productId);
        Employee result = getProductInfoCommand.execute();
        System.out.println(JSON.toJSONString(result));
        return "success";
    }

    @RequestMapping("/getProductInfos")
    @ResponseBody
    public String getProductInfos(@RequestParam List<Integer> ids){
        HystrixObservableCommand<Employee> getProductInfosCommand = new GetProductInfosCommand(ids);
        Observable<Employee> observe = getProductInfosCommand.observe();
        observe.subscribe(new Observer<Employee>() {
            @Override
            public void onCompleted() {
                System.out.println("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Employee employee) {
                System.out.println("employ:"+JSONObject.toJSONString(employee));
            }
        });
        return "success";
    }
}
