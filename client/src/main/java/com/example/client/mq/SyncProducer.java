package com.example.client.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/3
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception{
        final DefaultMQProducer producer = new DefaultMQProducer("last_producer");
        //需要设置nameServer地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for(int i = 0; i<10;i++){
            new Thread(){
                public void run(){
                    while (true){
                        try {
                            Message message = new Message("TopicTest", "TagA", "Test".getBytes(RemotingHelper.DEFAULT_CHARSET));
                            try {
                                producer.send(message);
                            } catch (MQClientException e) {
                                e.printStackTrace();
                            } catch (RemotingException e) {
                                e.printStackTrace();
                            } catch (MQBrokerException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        while (true){
            Thread.sleep(1000);
        }
    }
}
