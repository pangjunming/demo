package com.example.server.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.example.server.domain.Employee;
import com.example.server.util.HttpClientUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */
public class GetProductInfoCommand extends HystrixCommand<Employee>{
    private Integer productId;

    public GetProductInfoCommand(Integer productId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommand"));
        this.productId = productId;
    }

    @Override
    protected Employee run() throws Exception {
        try {
            String url = "http://127.0.0.1:8081/product/getProductInfo?productId=" + productId;
            String response = HttpClientUtil.sendGetRequest(url);
            System.out.println(response);
            return JSONObject.parseObject(response, Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
