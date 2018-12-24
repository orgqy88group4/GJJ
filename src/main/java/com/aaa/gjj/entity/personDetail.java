package com.aaa.gjj.entity;

/**
 * className:personDetail
 * discription:
 * author:qcm
 * createTime:2018-12-12 15:51
 */
public class personDetail {
    private String GRZH;  		//个人账号
    private String tb_pName; 	//员工姓名
    private String uname;		//所在单位
    private String paOp;		//创建人 操作人
    private double perMonPaysum;//个人缴纳
    private double unitMonPaysum;//单位缴纳
    private double sumPayment;   //总缴纳
    private String time; 		 //时间


    public personDetail(String gRZH, String tb_pName, String uname,String paOp, double perMonPaysum, double unitMonPaysum,String time) {
        super();
        GRZH = gRZH;
        this.tb_pName = tb_pName;
        this.uname = uname;
        this.paOp = paOp;
        this.perMonPaysum = perMonPaysum;
        this.unitMonPaysum = unitMonPaysum;
        this.sumPayment = perMonPaysum+unitMonPaysum;
        this.time = time;
    }


    public personDetail() {
        super();
    }


    public String getGRZH() {
        return GRZH;
    }
    public void setGRZH(String gRZH) {
        GRZH = gRZH;
    }
    public String getTb_pName() {
        return tb_pName;
    }
    public void setTb_pName(String tb_pName) {
        this.tb_pName = tb_pName;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPaOp() {
        return paOp;
    }
    public void setPaOp(String paOp) {
        this.paOp = paOp;
    }
    public double getPerMonPaysum() {
        return perMonPaysum;
    }
    public void setPerMonPaysum(double perMonPaysum) {
        this.perMonPaysum = perMonPaysum;
    }
    public double getUnitMonPaysum() {
        return unitMonPaysum;
    }
    public void setUnitMonPaysum(double unitMonPaysum) {
        this.unitMonPaysum = unitMonPaysum;
    }
    public double getSumPayment() {
        return sumPayment;
    }
    public void setSumPayment(double sumPayment) {
        this.sumPayment = sumPayment;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
