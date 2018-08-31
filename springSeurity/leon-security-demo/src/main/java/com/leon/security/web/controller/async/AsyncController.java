package com.leon.security.web.controller.async;

import com.leon.security.dto.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: chongwang
 * @Date: 2018/5/19 11:19
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception{
        logger.info("主线程开始");
        String orderName = RandomStringUtils.randomAlphabetic(8);
        mockQueue.setPlaceOrder(orderName);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderName, result);
        /*Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程结束");
                return "success";
            }
        };*/
        logger.info("主线程返回");
        return result;
    }

   public static void main(String[] args) {
       List<User> users = new ArrayList<>();
       User user1 = new User();
       user1.setUserName("张东升");
       User user2 = new User();
       user2.setUserName("Leon");
       User user3 = new User();
       user3.setUserName("阿里");
       User user4 = new User();
       user4.setUserName("京东");

       users.add(user1);
       users.add(user2);
       users.add(user3);
       users.add(user4);
       //users.stream().map(u -> u.getUserName()).forEach(System.out::print);
       users.stream().map(user -> user.getUserName()).collect(Collectors.joining(","));

       //users.forEach(System.out::print);
       //System.out.println(users.stream().map(User::getUserName).collect(Collectors.toList()));

       /*List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
        ExcelWriter writer = ExcelUtil.getWriter("/Users/chongwang/Desktop/1234.xlsx");
        writer.passCurrentRow();
        writer.merge(2);
        //writer.write(rows);
        //writer.setOrCreateSheet("123").resetRow().write(rows);
        //writer.setOrCreateSheet("456").resetRow().write(rows);
        //关闭writer，释放内存
        writer.close();*/
    }

}
