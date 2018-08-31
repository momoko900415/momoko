package com.leon.security.web.controller;

import cn.hutool.core.io.IoUtil;
import com.leon.security.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Auther: chongwang
 * @Date: 2018/5/17 22:58
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "/Users/chongwang/IdeaProjects/springSeurity/leon-security-demo/src/main/java/com/leon/security/web/controller";


    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream = response.getOutputStream();){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IoUtil.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
