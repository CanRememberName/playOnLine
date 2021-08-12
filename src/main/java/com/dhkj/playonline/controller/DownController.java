package com.dhkj.playonline.controller;


import com.dhkj.playonline.service.FileServiceImpl;
import com.dhkj.playonline.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

@Controller
public class DownController {

    @Autowired
    private FileServiceImpl fileService;

    IpUtils ip = new IpUtils();

    @RequestMapping("/downloadPlayer")
    public String downPlayer(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String path = "D:\\res\\player\\PotPlayer64.rar";
        //获取文件名
        File file = new File(path);
        long length = file.length();
        response.reset();//设置页面不缓存
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");//二进制传输数据
        response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode("PotPlayer64.rar","UTF-8"));
        response.setHeader("Content-Length", String.valueOf(file.length()));

        FileInputStream is = new FileInputStream(file);

        ServletOutputStream os = response.getOutputStream();

        byte[] buff = new byte[1024];

        int len = 0;

        while ((len = is.read(buff)) != -1) {
            os.write(buff,0,len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/allFile";
    }

    @RequestMapping("/TodownloadFile")
    public String toDownload() {
        return "forward:/downloadFiles";
    }

    @RequestMapping("/downloadFiles")
    public String fileDownload(HttpServletResponse response, HttpServletRequest request, @RequestParam("name") String name) throws Exception {

        com.dhkj.playonline.pojo.File myFile = fileService.searchFileByAnotherName(name);

        String path = "D:\\res\\v6speedDownload\\qiangujuecheng\\";
        String path2 = "D:\\res\\pic";
        //获取文件名
        String filename = myFile.getFileName();
        File file = new File(path,filename);
        if (!file.exists()) {
            file = new File(path2,filename);
        }
        long length = file.length();

        response.reset();//设置页面不缓存

        response.setCharacterEncoding("UTF-8");

        response.setContentType("multipart/form-data");//二进制传输数据

        response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode(filename,"UTF-8"));
        response.setHeader("Content-Length", String.valueOf(file.length()));




        FileInputStream is = new FileInputStream(file);

        ServletOutputStream os = response.getOutputStream();

        byte[] buff = new byte[1024];

        int len = 0;

        while ((len = is.read(buff)) != -1) {
            os.write(buff,0,len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/allFile";
    }
}
