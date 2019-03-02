package com.helper.pojo;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Homework {
    @Id
    @Column(name = "h_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hId;

    @Column(name = "h_desc")
    private String hDesc;

    @Column(name = "t_id")
    private String tId;

    @Column(name = "c_id")
    private String cId;

    @Column(name = "h_start_time")
    private Long hStartTime;

    @Column(name = "h_end_time")
    private Long hEndTime;

    @Column(name = "h_folder_name")
    private String hFolderName;

    public String gethFolderName() {
        return hFolderName;
    }

    public void sethFolderName(String hFolderName) {
        this.hFolderName = hFolderName;
    }

    public Long gethEndTime() {
        return hEndTime;
    }

    public void sethEndTime(Long hEndTime) {
        this.hEndTime = hEndTime;
    }

    public Long gethStartTime() {
        return hStartTime;
    }

    public void sethStartTime(Long hStartTime) {
        this.hStartTime = hStartTime;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    /**
     * @return h_id
     */
    public Long gethId() {
        return hId;
    }

    /**
     * @param hId
     */
    public void sethId(Long hId) {
        this.hId = hId;
    }

    /**
     * @return h_desc
     */
    public String gethDesc() {
        return hDesc;
    }

    /**
     * @param hDesc
     */
    public void sethDesc(String hDesc) {
        this.hDesc = hDesc;
    }

    /**
     * @return t_id
     */
    public String gettId() {
        return tId;
    }

    /**
     * @param tId
     */
    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "hId=" + hId +
                ", hDesc='" + hDesc + '\'' +
                ", tId='" + tId + '\'' +
                ", cId='" + cId + '\'' +
                ", hStartTime='" + hStartTime + '\'' +
                '}';
    }


    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date1 = new Date(1549617143852L);
        Date date2 = new Date(1551974400000L);
        System.out.println(dateFormat.format(date1));
        System.out.println(dateFormat.format(date2));
        System.out.println(date1.toString());
        System.out.println(date2.toString());
    }
}