package com.helper.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HomeworkAndStatus {

    private Long hId;


    private String hDesc;


    private String hTime;

    private String tName;

    private Byte sFinish;




    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Byte getsFinish() {
        return sFinish;
    }

    public void setsFinish(Byte sFinish) {
        this.sFinish = sFinish;
    }

    public String gethTime() {
        return hTime;
    }

    public void sethTime(String hTime) {
        this.hTime = hTime;
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

}