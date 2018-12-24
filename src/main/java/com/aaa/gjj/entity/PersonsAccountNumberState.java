package com.aaa.gjj.entity;
/**
 *@className:PersonsAccountNumberState.java
 *@scription:
 *@author:ZHEN
 *@createTime:2018-10-8上午11:15:39
 *@version:1.0.0
 */
/**
 * 封存 启封 销户 实体类
 * @author 呐喊。
 *
 */
public class PersonsAccountNumberState {
	private String grzh;        //个人账号
	private String tb_pName; 	//账户姓名
	private String ubitNrop;	//单位缴存比例
	private String indiNrop; 	//个人缴存比例
	private double baseNummber; //缴存基数
	private double dalance; 	//个人公积金余额
	private String peraccState;
	private String lastNaydate; //最后汇缴月
	
	public PersonsAccountNumberState() {
		super();
	}
	public PersonsAccountNumberState(String gRZH, String tb_pName,
                                     String ubitNrop, String indiNrop, double baseNummber,
                                     double dalance, String peraccState, String lastNaydate) {
		super();
		this.grzh = gRZH;
		this.tb_pName = tb_pName;
		this.ubitNrop = ubitNrop;
		this.indiNrop = indiNrop;
		this.baseNummber = baseNummber;
		this.dalance = dalance;
		this.peraccState = peraccState;
		this.lastNaydate = lastNaydate;
	}	
	public String getGrzh() {
		return grzh;
	}
	public void setGrzh(String grzh) {
		this.grzh = grzh;
	}
	public String getTb_pName() {
		return tb_pName;
	}
	public void setTb_pName(String tb_pName) {
		this.tb_pName = tb_pName;
	}
	public String getUbitNrop() {
		return ubitNrop;
	}
	public void setUbitNrop(String ubitNrop) {
		this.ubitNrop = ubitNrop;
	}
	public String getIndiNrop() {
		return indiNrop;
	}
	public void setIndiNrop(String indiNrop) {
		this.indiNrop = indiNrop;
	}
	public double getBaseNummber() {
		return baseNummber;
	}
	public void setBaseNummber(double baseNummber) {
		this.baseNummber = baseNummber;
	}
	public double getDalance() {
		return dalance;
	}
	public void setDalance(double dalance) {
		this.dalance = dalance;
	}
	public String getPeraccState() {
		return peraccState;
	}
	public void setPeraccState(String peraccState) {
		this.peraccState = peraccState;
	}
	public String getLastNaydate() {
		return lastNaydate;
	}
	public void setLastNaydate(String lastNaydate) {
		this.lastNaydate = lastNaydate;
	}
	
}	
