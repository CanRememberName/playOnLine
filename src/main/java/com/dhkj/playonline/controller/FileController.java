package com.dhkj.playonline.controller;


import com.dhkj.playonline.pojo.File;
import com.dhkj.playonline.service.FileServiceImpl;
import com.dhkj.playonline.utils.FileUtils;
import com.dhkj.playonline.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping("/allFile")
    public String allFile(Model model, @RequestParam("page") String page1) {
        List<File> list = fileService.getAllFile();
        ArrayList<String> source = new ArrayList<>();
        //由于编号是主键，所以需要将其调整为从一开始
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setFileId(i+1);
            source.add(new IpUtils().sourceAddress(list.get(i)));
        }
        //获取大小
        //现在默认每页显示10个记录
        int size = list.size();
        //页数
        int pageNUm = size / 10;
        if (size % 10 != 0){
            pageNUm++;
        }
        int page = Integer.valueOf(page1);
        if (page <= pageNUm) {
            List<File> rlist = new ArrayList<>();
            int begin = (page-1) * 10;
            int end = page * 10 - 1;
            for (int i = begin; i <= end; i++) {
                if (i >= size) {
                    break;
                }
                rlist.add(list.get(i));
            }
            model.addAttribute("list",rlist);
            model.addAttribute("source", source);
            model.addAttribute("size",list.size());
        }
        ArrayList<Integer> pages = new ArrayList<>();
        for (int i = 0; i < pageNUm; i++) {
            pages.add(i+1);
        }
        model.addAttribute("pageNum",pages);
        model.addAttribute("Maxpage",pageNUm);
        model.addAttribute("beforePage",page-1);
        model.addAttribute("afterPage",page+1);
        System.out.println(pages.size());
        return "funPage/allFile";
    }


    @RequestMapping("/toUpLoad")
    public String toUpLoad() {
        return "funPage/upload";
    }

    @RequestMapping("/toPlayVideo")
    public String toPlay() {
        return "forward:/playVideo";
    }

    @RequestMapping("/scan")
    public String scanAllFile(HttpServletRequest request) {
        java.io.File path = new java.io.File("D:/res/pic/");
        java.io.File path1 = new java.io.File("D:/res/v6speedDownload/");
        FileUtils fileUtils = new FileUtils();
        ArrayList<java.io.File> files = new ArrayList<>();
        fileUtils.scanFile(files, path);
        System.out.println("已扫描" + path);
        fileUtils.scanFile(files,path1);
        System.out.println("已扫描" + path1);
        //删除数据库中的文件
        fileService.deleteAllFileBeforeSearch();
        for (java.io.File file : files) {
            File needInsert = new File();
            //设置名字
            needInsert.setFileName(file.getName());
            //设置描述
            needInsert.setFileDetail("由扫描上传");
            //设置文件大小
            float v = (float) (file.length() / 1024.0 / 1024.0);
            if (v<0.05) {
                v = v * 1024;
                DecimalFormat df = new DecimalFormat("#.00");
                String format = df.format(v);
                needInsert.setFileLength(format + "KB");
            } else {
                DecimalFormat df = new DecimalFormat("#.00");
                String format = df.format(v);
                needInsert.setFileLength(format + "MB");
            }

            //设置别名
            needInsert.setAnotherName(file.getName());
            //设置上传者
            needInsert.setUpPerson("dihuangkaijia");
            fileService.insertFile(needInsert);
        }
        return "redirect:/allFile";
    }

    @RequestMapping("/allMusic")
    private String allMusic(Model model) {
        List<File> listAll = fileService.getAllFile();
        ArrayList<String> source = new ArrayList<>();
        //由于编号是主键，所以需要将其调整为从一开始
        //获取大小
        List<File> list = new ArrayList<>();
        for (int i = 0; i < listAll.size(); i++) {
            String fileName = listAll.get(i).getFileName();
            if (fileName.toLowerCase().endsWith(".mp3") || fileName.toLowerCase().endsWith(".ogg") || fileName.toLowerCase().endsWith(".wav")) {
                list.add(listAll.get(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setFileId(i+1);
            source.add(new IpUtils().sourceAddress(list.get(i)));
            System.out.println(list.get(i).getFileName());
        }
        model.addAttribute("list",list);
        model.addAttribute("source", source);
        model.addAttribute("size",list.size());
        return "funPage/allFile";
    }

    @RequestMapping("/allVideo")
    private String allVideo(Model model) {
        List<File> listAll = fileService.getAllFile();
        ArrayList<String> source = new ArrayList<>();
        //由于编号是主键，所以需要将其调整为从一开始
        //获取大小
        List<File> list = new ArrayList<>();
        for (int i = 0; i < listAll.size(); i++) {
            String fileName = listAll.get(i).getFileName();
            if (fileName.toLowerCase().endsWith(".mp4") || fileName.toLowerCase().endsWith(".ogg") || fileName.toLowerCase().endsWith(".webm")) {
                list.add(listAll.get(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setFileId(i+1);
            source.add(new IpUtils().sourceAddress(list.get(i)));
            System.out.println(list.get(i).getFileName());
        }
        model.addAttribute("list",list);
        model.addAttribute("source", source);
        model.addAttribute("size",list.size());
        return "funPage/allFile";
    }


    //搜索文件
    @RequestMapping("/search")
    public String search(@RequestParam("filename") String name, Model model) {
        List<File> list = fileService.selectFileByName(name);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setFileId(i+1);
        }
        model.addAttribute("list",list);
        model.addAttribute("size",list.size());
        return "funPage/allFile";
    }
}
