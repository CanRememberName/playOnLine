package com.dhkj.playonline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private int fileId;
    private String fileName;
    private String fileLength;
    private String fileDetail;
    private String upPerson;
    private String upIP;
    private Date latestTime;
    private String anotherName;

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileLength='" + fileLength + '\'' +
                ", fileDetail='" + fileDetail + '\'' +
                ", upPerson='" + upPerson + '\'' +
                ", upIP='" + upIP + '\'' +
                ", latestTime=" + latestTime +
                ", anotherName='" + anotherName + '\'' +
                '}';
    }
}
