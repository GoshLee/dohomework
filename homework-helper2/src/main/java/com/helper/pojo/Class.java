package com.helper.pojo;

import javax.persistence.*;

public class Class {
    /**
     * 班级主键
     */
    @Id
    @Column(name = "c_id")
    private String cId;

    /**
     * 班级总人数
     */
    @Column(name = "c_members")
    private Integer cMembers;

    /**
     * 班级名称
     */
    @Column(name = "c_name")
    private String cName;

    /**
     * 获取班级主键
     *
     * @return c_id - 班级主键
     */
    public String getcId() {
        return cId;
    }

    /**
     * 设置班级主键
     *
     * @param cId 班级主键
     */
    public void setcId(String cId) {
        this.cId = cId;
    }

    /**
     * 获取班级总人数
     *
     * @return c_members - 班级总人数
     */
    public Integer getcMembers() {
        return cMembers;
    }

    /**
     * 设置班级总人数
     *
     * @param cMembers 班级总人数
     */
    public void setcMembers(Integer cMembers) {
        this.cMembers = cMembers;
    }

    /**
     * 获取班级名称
     *
     * @return c_name - 班级名称
     */
    public String getcName() {
        return cName;
    }

    /**
     * 设置班级名称
     *
     * @param cName 班级名称
     */
    public void setcName(String cName) {
        this.cName = cName;
    }
}