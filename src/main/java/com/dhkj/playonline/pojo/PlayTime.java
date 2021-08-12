package com.dhkj.playonline.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayTime {
    private int id;
    private String fileName;
    private int playTime;
    private String userIp;

    @Override
    public String toString() {
        return "PlayTime{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", playTime=" + playTime +
                ", userIp='" + userIp + '\'' +
                '}';
    }
}
