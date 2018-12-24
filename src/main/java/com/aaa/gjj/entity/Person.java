package com.aaa.gjj.entity;

/**
 * className:Person
 * discription:
 * author:qcm
 * createTime:2018-12-11 21:06
 */

/**
 * 人员转移实体类
 */
public class Person {
    private String tb_pName;
    private String tb_idNUmber;
    private String dalance;
    private String peraccState;
    public Person(String tb_pName, String tb_idNUmber, String dalance,
                  String peraccState) {
        super();
        this.tb_pName = tb_pName;
        this.tb_idNUmber = tb_idNUmber;
        this.dalance = dalance;
        this.peraccState = peraccState;
    }
    public Person() {
        super();
    }

    public String getTb_pName() {
        return tb_pName;
    }
    public void setTb_pName(String tb_pName) {
        this.tb_pName = tb_pName;
    }
    public String getTb_idNUmber() {
        return tb_idNUmber;
    }
    public void setTb_idNUmber(String tb_idNUmber) {
        this.tb_idNUmber = tb_idNUmber;
    }
    public String getDalance() {
        return dalance;
    }
    public void setDalance(String dalance) {
        this.dalance = dalance;
    }
    public String getPeraccState() {
        return peraccState;
    }
    public void setPeraccState(String peraccState) {
        this.peraccState = peraccState;
    }
}
