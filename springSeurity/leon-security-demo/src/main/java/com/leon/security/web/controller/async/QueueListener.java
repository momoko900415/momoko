package com.leon.security.web.controller.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Auther: chongwang
 * @Date: 2018/5/20 11:54
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() ->{
        while (true){
            if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                String orderNumber = mockQueue.getCompleteOrder();
                logger.info("返回订单处理结果"+orderNumber);
                deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                mockQueue.setCompleteOrder(null);
            }else{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        }).start();
    }
}
