package com.helper.pojo;

import javax.persistence.*;

public class Status {
    /**
     * 学生id
     */
    @Column(name = "s_id")
    private String sId;

    /**
     * 作业id
     */
    @Column(name = "h_id")
    private Long hId;

    /**
     * 作业完成情况（1表示完成，0表示未完成）
     */
    @Column(name = "s_finish")
    private Byte sFinish;

    /**
     * 班级
     */
    @Column(name = "c_id")
    private String cId;

    /**
     * 获取学生id
     *
     * @return s_id - 学生id
     */
    public String getsId() {
        return sId;
    }

    /**
     * 设置学生id
     *
     * @param sId 学生id
     */
    public void setsId(String sId) {
        this.sId = sId;
    }

    /**
     * 获取作业id
     *
     * @return h_id - 作业id
     */
    public Long gethId() {
        return hId;
    }

    /**
     * 设置作业id
     *
     * @param hId 作业id
     */
    public void sethId(Long hId) {
        this.hId = hId;
    }

    /**
     * 获取作业完成情况（1表示完成，0表示未完成）
     *
     * @return s_finish - 作业完成情况（1表示完成，0表示未完成）
     */
    public Byte getsFinish() {
        return sFinish;
    }

    /**
     * 设置作业完成情况（1表示完成，0表示未完成）
     *
     * @param sFinish 作业完成情况（1表示完成，0表示未完成）
     */
    public void setsFinish(Byte sFinish) {
        this.sFinish = sFinish;
    }

    /**
     * 获取班级
     *
     * @return c_id - 班级
     */
    public String getcId() {
        return cId;
    }

    /**
     * 设置班级
     *
     * @param cId 班级
     */
    public void setcId(String cId) {
        this.cId = cId;
    }
}