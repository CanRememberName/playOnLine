package com.dhkj.playonline.controller;

import com.dhkj.playonline.pojo.File;
import com.dhkj.playonline.service.FileServiceImpl;
import com.dhkj.playonline.utils.HaveUpFileSize;
import com.dhkj.playonline.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

@Controller
public class UpController {

    @Autowired
    private FileServiceImpl fileService;

    IpUtils ip = new IpUtils();

    @RequestMapping("/uploadfile")
    public String up(@RequestParam("file") MultipartFile file, @RequestParam("anotherName") String anotherName,
                     @RequestParam("filedetail") String filedetail, @RequestParam("upperson") String upperson,
                     HttpServletRequest request, File myFile, Model model) throws IOException {
        request.setCharacterEncoding("utf-8");

        //new Thread(new HaveUpFileSize(file)).start();
        System.out.println(file.getSize());
        String path = "D:\\res\\pic";
        java.io.File realPath = new java.io.File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        String filename = file.getOriginalFilename();
        myFile.setFileName(filename);
        long size = file.getSize();
        float v = (float) (size / 1024.0 / 1024.0);
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(v);
        if (format.startsWith(".")) {
            format = "0" + format;
        }
        myFile.setFileLength(format + "MB");
        myFile.setAnotherName(anotherName);
        myFile.setFileDetail(filedetail);
        myFile.setUpPerson(upperson);
        myFile.setUpIP(ip.getIPofUser(request));
        file.transferTo(new java.io.File(realPath + "\\" + file.getOriginalFilename()));
        fileService.insertFile(myFile);

        return "redirect:/allFile";
    }
}
