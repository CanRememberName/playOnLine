package com.dhkj.playonline.controller;


import com.dhkj.playonline.pojo.File;
import com.dhkj.playonline.pojo.PlayTime;
import com.dhkj.playonline.service.FileServiceImpl;
import com.dhkj.playonline.service.PlayServiceImpl;
import com.dhkj.playonline.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class PlayController {
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private IpUtils ipUtils;
    @Autowired
    private PlayServiceImpl playService;

    @RequestMapping("/playVideo")
    public String playVideo(Model model, HttpServletResponse response, HttpServletRequest request, @RequestParam("name") String name) {
        //System.out.println(name);
/*        List<File> allFile = fileService.getAllFile();
        File file = new File();
        for (File f : allFile) {
            if (f.getAnotherName().equals(name)) {
                file = f;
            }
        }*/
        File file = fileService.searchFileByAnotherName(name);
        //System.out.println(file);
        //获取文件名
        InetAddress ip4 = null;
        String fileName = file.getFileName();
        try {
            ip4 = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String path = "http://" + ip4.getHostAddress() + ":8080" + "/img/" + fileName;
        System.out.println(path);
        model.addAttribute("source", path);
        String imgPath = "http://" + ip4.getHostAddress() + ":8080" + "/img/team.jpg";
        //添加播放前的图像
        model.addAttribute("img",imgPath);
        PlayTime playTime = playService.slectPlayTimeByFileName(name,ipUtils.getIPofUser(request));
        if (playTime == null || !playTime.getUserIp().equals(ipUtils.getIPofUser(request))) {
            PlayTime p = new PlayTime();
            p.setPlayTime(1);
            p.setFileName(name);
            p.setUserIp(ipUtils.getIPofUser(request));
            playService.insertPlayTime(p);
        } else if (playTime.getUserIp().equals(ipUtils.getIPofUser(request))){
            playService.updatePlayTime(name,ipUtils.getIPofUser(request));
        }
        //System.out.println(10000);
        //获取当前ip地址
        return "funPage/playVideo";
    }


    @RequestMapping("/toPlayMusic")
    public String playMusic(Model model, HttpServletResponse response, HttpServletRequest request, @RequestParam("name") String name) {
        File file = fileService.searchFileByAnotherName(name);
        //获取文件名
        InetAddress ip4 = null;
        String fileName = file.getFileName();
        try {
            ip4 = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String path = "http://" + ip4.getHostAddress() + ":8080" + "/img/" + fileName;
        model.addAttribute("source", path);
        System.out.println(path);
        //获取当前ip地址
        return "funPage/playMusic";
    }

}
