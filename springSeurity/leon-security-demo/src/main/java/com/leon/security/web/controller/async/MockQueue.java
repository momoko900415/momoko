package com.leon.security.web.controller.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Auther: chongwang
 * @Date: 2018/5/19 12:07
 */
@Component
public class MockQueue {

    private String placeOrder;

    private String completeOrder;

    private static final Logger logger = LoggerFactory.getLogger(MockQueue.class);

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() ->{
        logger.info("接到下单请求" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
        logger.info("下单请求处理完毕" + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
