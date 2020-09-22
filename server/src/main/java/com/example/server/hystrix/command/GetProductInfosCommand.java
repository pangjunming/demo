package com.example.server.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.example.server.domain.Employee;
import com.example.server.util.HttpClientUtil;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/19
 */
public class GetProductInfosCommand extends HystrixObservableCommand<Employee> {

    private List<Integer> productIds;

    public GetProductInfosCommand(List<Integer> productIds) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfosCommand"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<Employee> construct() {
        return Observable.create(new Observable.OnSubscribe<Employee>() {
            @Override
            public void call(Subscriber<? super Employee> subscriber) {
                if(subscriber.isUnsubscribed()){
                    for(Integer id : productIds){
                        String url = "http://127.0.0.1:8081/product/getProductInfo?productId=" + id;
                        try {
                            String response = HttpClientUtil.sendGetRequest(url);
                            System.out.println(response);
                            Employee employee = JSONObject.parseObject(response, Employee.class);
                            subscriber.onNext(employee);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }
}
