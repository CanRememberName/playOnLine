package com.dhkj.playonline.utils;

import com.dhkj.playonline.service.FileService;

import java.io.File;
import java.util.List;

public class FileUtils {

    public void scanFile(List<File> fileList, File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                scanFile(fileList, file1);
            }
        } else {
            if (!file.getName().equals("exam.mp4") && !file.getName().equals("test.mp4") && !file.getName().equals("team.jpg")){
                fileList.add(file);
            }
        }
    }
}
