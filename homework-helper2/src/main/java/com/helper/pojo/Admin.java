package com.helper.pojo;

import javax.persistence.*;

public class Admin {
    @Id
    @Column(name = "a_id")

    private String aId;

    @Column(name = "a_name")
    private String aName;

    private String password;

    @Column(name = "r_id")
    private Byte rId;

    /**
     * @return a_id
     */
    public String getaId() {
        return aId;
    }

    /**
     * @param aId
     */
    public void setaId(String aId) {
        this.aId = aId;
    }

    /**
     * @return a_name
     */
    public String getaName() {
        return aName;
    }

    /**
     * @param aName
     */
    public void setaName(String aName) {
        this.aName = aName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return r_id
     */
    public Byte getrId() {
        return rId;
    }

    /**
     * @param rId
     */
    public void setrId(Byte rId) {
        this.rId = rId;
    }
}