package com.example.client.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/8
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {
        //事务消息，用来接收mq回调的一个监听器接口
        TransactionListener transactionListener = new TransactionListener() {

            //zhelk这里实现本事务
       @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

           try {
               return LocalTransactionState.COMMIT_MESSAGE;
           } catch (Exception e) {
               return LocalTransactionState.ROLLBACK_MESSAGE;
           }
       }
            //这里回调补偿任务
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                return null;
            }
        };

        //创建一个支持事务消息的producer,指定一个生产者分组
        TransactionMQProducer producer = new TransactionMQProducer("testProducerGroup");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("testThread");
                return thread;
            }
        });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
        Message message = new Message("PayOrderSuccessTopic","TestTag", "TestKey","订单支付消息".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //将消息以事务形式发送
        TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message, null);
    }
}
