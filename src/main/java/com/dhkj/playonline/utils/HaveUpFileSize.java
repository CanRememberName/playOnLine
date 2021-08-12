package com.dhkj.playonline.utils;

import org.springframework.web.multipart.MultipartFile;

public class HaveUpFileSize implements Runnable{
    private MultipartFile file;

    public HaveUpFileSize(MultipartFile file) {
        this.file = file;
    }

    @Override
    public void run() {
        while (file!=null) {
            System.out.println(file.getSize());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
