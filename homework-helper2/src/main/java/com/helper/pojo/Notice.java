package com.helper.pojo;

import javax.persistence.*;

public class Notice {
    @Id
    @Column(name = "n_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nId;

    @Column(name = "n_desc")
    private String nDesc;

    @Column(name = "a_id")
    private Long aId;

    /**
     * @return n_id
     */
    public Long getnId() {
        return nId;
    }

    /**
     * @param nId
     */
    public void setnId(Long nId) {
        this.nId = nId;
    }

    /**
     * @return n_desc
     */
    public String getnDesc() {
        return nDesc;
    }

    /**
     * @param nDesc
     */
    public void setnDesc(String nDesc) {
        this.nDesc = nDesc;
    }

    /**
     * @return a_id
     */
    public Long getaId() {
        return aId;
    }

    /**
     * @param aId
     */
    public void setaId(Long aId) {
        this.aId = aId;
    }
}