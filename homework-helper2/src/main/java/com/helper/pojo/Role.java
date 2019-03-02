package com.helper.pojo;

import javax.persistence.*;

public class Role {
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte rId;

    @Column(name = "r_name")
    private String rName;

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

    /**
     * @return r_name
     */
    public String getrName() {
        return rName;
    }

    /**
     * @param rName
     */
    public void setrName(String rName) {
        this.rName = rName;
    }
}