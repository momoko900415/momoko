package com.leon.security.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @Auther: chongwang
 * @Date: 2018/4/25 21:38
 */

@Service
@RequestMapping("/file")
public class DownLoadTest {

    @RequestMapping("/down")
    public void downLoad() throws IOException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", "leon");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setContentType("application/x-download");
        //HttpUtil.post("http://192.168.2.234:8080/file/123", param);
        String url = "http://192.168.2.234:8080/file/123";
        HttpResponse response1 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")
                .form(param)
                .execute();
        InputStream inputStream = response1.bodyStream();
        OutputStream ops = response.getOutputStream();
        IoUtil.copy(inputStream,ops);
        ops.flush();
        ops.close();
    }
}
