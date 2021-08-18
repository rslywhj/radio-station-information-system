package com.tjetc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Fdjjr {
    private Long id;

    private String jqmc;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date kssj;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jssj;

    private String lx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJqmc() {
        return jqmc;
    }

    public void setJqmc(String jqmc) {
        this.jqmc = jqmc == null ? null : jqmc.trim();
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }
}