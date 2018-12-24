package com.aaa.gjj.service;

import com.aaa.gjj.dao.LoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:LoanServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-18 11:04
 */
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanDao loanDao;

    /**
     * 查询贷款信息表，返回贷款信息的账号
     * @return
     */
    @Override
    public List<Map> getgrzh() {
        return loanDao.getgrzh();
    }

    /**
     * 根据前台传来的个人账号，来查询账户信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getList(Map map) {
        return loanDao.getList(map);
    }

    /**
     * 个人贷款模块 --共同借款人--前台传来共同贷款人账号
     * @param map
     * @return
     */
    @Override
    public List<Map> getTogether(Map map) {
        return loanDao.getTogether(map);
    }

    /**
     * 把贷款数据存到数据库里面
     * @param map
     * @return
     */
    @Override
    public int getInfo(Map map) {
        return loanDao.getInfo(map);
    }

    /**
     * 贷款初审
     * @param map
     * @return
     */
    @Override
    public List<Map> getPage(Map map) {
        return loanDao.getPage(map);
    }

    /**
     * 查询贷款初审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount = loanDao.getPageCount(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 点击详细按钮，前台传来个人账户，根据个人账户来查询详细信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getPersonInfo(Map map) {
        return loanDao.getPersonInfo(map);
    }

    /**
     * 点击审核通过按钮，将数据插入到终审表 修改状态为2
     * @param map
     * @return
     */
    @Override
    public int PassCheck(Map map) {
        return loanDao.PassCheck(map);
    }

    /**
     * 初审驳回按钮，将数据插入到终审表 插入时状态为3
     * @param map
     * @return
     */
    @Override
    public int reject(Map map) {
        return loanDao.reject(map);
    }

    /**
     * 初审通过，将状态修改位2
     * @param map
     * @return
     */
    @Override
    public int PassChangeState(Map map) {
        return loanDao.PassChangeState(map);
    }

    /**
     * 贷款终审
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageFinal(Map map) {
        return loanDao.getPageFinal(map);
    }

    /**
     * 查询贷款终审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    @Override
    public int getPageFinalCount(Map map) {
        List<Map> pageCountFinal = loanDao.getPageFinalCount(map);
        if(pageCountFinal!=null&&pageCountFinal.size()>0){
            return Integer.valueOf(pageCountFinal.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 点击贷款终审，确认驳回按钮，状态变为5
     * @param map
     * @return
     */
    @Override
    public int SureReject(Map map) {
        return loanDao.SureReject(map);
    }

    /**
     * 终审通过之后  把信息存进还款明细 计算出应还本金 利息等
     * 具体的计算公式是（等额本息还款）：
     * a=F*i(1+i)^n/[(1+i)^n-1]
     * a:月供;
     * F:贷款总额;
     * i:贷款利率(月利率),月利率=年利率/12;
     * n:还款月数
     * 总利息=月还款额*贷款月数-本金
     * Math.pow(m,n)，代表m的n次方
     * @param map
     * @return
     */
    @Override
    public int FinalPass(Map map) {
        String loan_rate = map.get("loan_rate")+"";//取贷款利率 计算还款利息和每月还款金额
        String loan_money = map.get("loan_money")+"";//取贷款总额
        String loan_periods = map.get("loan_periods")+"";//获取贷款期数
        int n = Integer.valueOf(loan_periods);//期数
        double F = Double.valueOf(loan_money);//贷款总额
        double rate = Double.valueOf(loan_rate)/100;//利率
        double i = rate/12 ;//月利率
        double a = F*i*Math.pow((1+i),n)/(Math.pow((1+i),n)-1);//计算月供
        map.put("repay_money", F);//应还本金
        map.put("repay_interests", (a*n)-F);//应还利息
        map.put("repay_month", a);//月还款
        return loanDao.FinalPass(map);
    }

    /**
     * 终审通过、修改贷款终审表的相关状态
     * @param map
     * @return
     */
    @Override
    public int passFinal(Map map) {
        return loanDao.passFinal(map);
    }

    /**
     * 销户审核，后台查询信息
     * @param map
     * @return
     */
    @Override
    public List<Map> QueryAuditInfomation(Map map) {
        return loanDao.QueryAuditInfomation(map);
    }

    /**
     * 销户审核后台查询信息总条数
     * @param map
     * @return
     */
    @Override
    public int QueryAuditInfomationCount(Map map) {
        List<Map> pageCountFinal = loanDao.QueryAuditInfomationCount(map);
        if(pageCountFinal!=null&&pageCountFinal.size()>0){
            return Integer.valueOf(pageCountFinal.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 操作审核表 点击通过按钮获取前台传来的值  传到后台用来修改个人账户状态
     * @param map
     * @return
     */
    @Override
    public int PersonAccountState(Map map) {
        Map tempMap =new HashMap();
        tempMap.put("pdstype",map.get("state"));//
        tempMap.put("grzh",map.get("grzh"));	//个人账户
        tempMap.put("resson",map.get("resson"));
        if (tempMap.size()>0){
            return loanDao.PersonAccountState(tempMap);
        }else {
            return 0;
        }
    }

    /**
     * 修改审核表中的audit_name字段，给位通过
     * @param map
     * @return
     */
    @Override
    public int unsealAuditUpdate(Map map) {
        Map tempMap =new HashMap();
        tempMap.put("audit_name",map.get("audit_name"));
        tempMap.put("unseal_id",map.get("unseal_id"));
        String bohui = "";
        if(map.get("bohui")==null){
            bohui = "0";
        }else{
            bohui = map.get("bohui")+"";
        }
        tempMap.put("bohui",bohui);
        return loanDao.unsealAuditUpdate(tempMap);
    }

    /**
     * 修改公司缴存人数
     * @param map
     * @return
     */
    @Override
    public int unitsUpdate(Map map) {
        return loanDao.unitsUpdate(map);
    }

    /**
     * 销户，封存 修改公司缴存总金额
     * @param map
     * @return
     */
    @Override
    public int unitsUpdateMoney(Map map) {
        return loanDao.unitsUpdateMoney(map);
    }

    /**
     *启封 修改公司缴存总金额
     * @param map
     * @return
     */
    @Override
    public int unitsMoney(Map map) {
        return loanDao.unitsMoney(map);
    }

    /**
     * 查询审核表中状态位1或2的信息 放入前台
     * @return
     */
    @Override
    public List<Map> unsealAuditSelect(Map map) {
        return loanDao.unsealAuditSelect(map);
    }

    /**
     * 1 为启封  2为封存  0为销户
     * 查询审核表中状态位1和2的
     * @param map
     * @return
     */
    @Override
    public int unsealAuditSelectCount(Map map) {
        List<Map> pageCountFinal = loanDao.unsealAuditSelectCount(map);
        if(pageCountFinal!=null&&pageCountFinal.size()>0){
            return Integer.valueOf(pageCountFinal.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 查询人员转移审核表信息
     * @param map
     * @return
     */
    @Override
    public List<Map> uditTransfer(Map map) {
        return loanDao.uditTransfer(map);
    }

    /**
     * 查询转移人员信息总条数
     * @param map
     * @return
     */
    @Override
    public int uditTransferCount(Map map) {
        List<Map> pageCountFinal = loanDao.uditTransferCount(map);
        if(pageCountFinal!=null&&pageCountFinal.size()>0){
            return Integer.valueOf(pageCountFinal.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 查询出要转入的公司
     * @param map
     * @return
     */
    @Override
    public int rejects(Map map) {
        List<Map> rejects = loanDao.rejects(map);//获取转入和转出公司名称和个人账号
        List<Map> list = loanDao.seletId(rejects.get(0).get("transfer_into_unit") + "");//通过转入公司名称查询到要转入公司id
        System.out.println("list返回的值："+list);
        System.out.println("uid="+list.get(0).get("uid"));
        if (rejects.size()>0&&list.size()>0){
            int uaid = Integer.valueOf(list.get(0).get("uid")+"");   //转入公司的id
            String GRZH = rejects.get(0).get("person_account")+"";  //个人账号
            int i = loanDao.upDateId(uaid,GRZH);//根据个人账号修改属于公司的id，修改成要转入的公司id
            if (i==1){
                loanDao.RejectDelete(map);//删除审核表中的信息
                //用转入公司获取到当前个人账户为正常的条数 然后修改 公司账户缴存人数
                loanDao.unitsUpdatec(rejects.get(0).get("transfer_into_unit")+"");
                //用转出公司获取到当前个人账户为正常的条数 然后修改 公司账户缴存人数
                loanDao.unitsUpdatea(rejects.get(0).get("transfer_out_unit")+"");
                //用转出公司获取到当前个人账户为正常的条数 然后修改 公司账户总缴存金额
                System.out.println("转出公司名称："+rejects.get(0).get("transfer_out_unit") + "");
                int i1 = loanDao.unitsUpdateMoneya(rejects.get(0).get("transfer_out_unit") + "");
                System.out.println("转出公司获"+i1);
                //用转入公司获取到当前个人账户为正常的条数 然后修改 公司账户总缴存金额
                System.out.println("转入公司名称："+rejects.get(0).get("transfer_into_unit") + "");
                int i2 = loanDao.unitsMoneya(rejects.get(0).get("transfer_into_unit") + "");
                System.out.println("转入公司获取"+i2);
            }
            return i;
        }else {
            return 0;
        }
    }

    @Override
    public List<Map> seletId(String name) {
        return null;
    }

    /**
     * 根据获取的个人账号查询到自己公司id 在修改为要转入的公司id
     * @param GRZH
     * @param uid
     * @return
     */
    @Override
    public int upDateId(String GRZH, int uid) {
        return 0;
    }

    /**
     * 公司缴存人数  查询到当前公司账户状态为正常的个数  把公司账户信息表 应缴纳人数修改了
     * @param uname
     * @return
     */
    @Override
    public int unitsUpdatec(String uname) {
        return 0;
    }

    /**
     * 根据前台传来的id，查询审核表中的信息
     * @param map
     * @return
     */
    @Override
    public List<Map> CheckAuditTable(Map map) {
        List<Map> recordName = loanDao.CheckAuditTable(map);
        System.out.println("ServiceImpl层，前台传来的值："+map);
        if (recordName.size()>0){
            Map tempMap = new HashMap();
            tempMap.put("transfer_out_unit", recordName.get(0).get("transfer_out_unit"));//转出单位
            tempMap.put("transfer_into_unit",recordName.get(0).get("transfer_into_unit"));//转入单位
            tempMap.put("auditor",recordName.get(0).get("auditor"));				//申请人
            tempMap.put("person_account",recordName.get(0).get("person_account"));//个人帐号
            tempMap.put("transfer_reason",recordName.get(0).get("transfer_reason"));	//转移原因
            tempMap.put("operator",recordName.get(0).get("operator"));				//操作人			（操作人待添加）
            tempMap.put("submit_time",recordName.get(0).get("submit_time"));			//提交时间
            tempMap.put("pname",recordName.get(0).get("pname"));					//待转移人员姓名
            tempMap.put("idNumber",recordName.get(0).get("idNumber"));			//证件号码
            tempMap.put("balance",recordName.get(0).get("balance"));			//个人账号余额
            tempMap.put("state",recordName.get(0).get("state"));//个人账号状态
            tempMap.put("baseNummber",recordName.get(0).get("baseNummber"));		//个人缴存基数
            tempMap.put("audit_state",map.get("state"));     //给记录表添加状态
            System.out.println("需要添加到记录表中的值："+tempMap);
            if (tempMap.size()>0){
                loanDao.addInfoToRecord(tempMap);
            }
        }
        return null;
    }

    /**
     * 添加到记录表 上面已调用
     * @param map
     * @return
     */
    @Override
    public int addInfoToRecord(Map map) {
        return 0;
    }

    /**
     * 个人贷款驳回，删除记录
     * @param map
     * @return
     */
    @Override
    public int RejectDelete(Map map) {
        return loanDao.RejectDelete(map);
    }

    /**
     * 获取还款信息表
     * @param id
     * @return
     */
    @Override
    public List<Map> getrepayinfo(Integer id) {
        return loanDao.getrepayinfo(id);
    }

    /**
     * 终审通过之后  把还款信息表内容添加到还款表  计算出应还本金 利息 应还等
     * @param map
     * @return
     */
    @Override
    public int insertRepay(Map map) {
        String time = map.get("ctime")+"";//获取放贷日期
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		/*Date date=null;*/
        double a=Double.parseDouble(map.get("repay_money")+"");//应还本金
        int p=Integer.valueOf(map.get("loan_periods")+"");//贷款期数
		/*int i=1;*/
        double c=Double.parseDouble(map.get("repay_interests")+"");//应还利息
        double r=Double.parseDouble(map.get("loan_rate")+"");//贷款利率
        double m=Double.parseDouble(map.get("repay_month")+"");//月还款
        map.put("repayed_All_money",0.00);//已还金额(包括本金和利息)
        double All=Double.parseDouble(map.get("repayed_All_money")+"");//已还金额(包括本金和利息)
        map.put("residue_periods",p);//剩余期数
        map.put("repayed_money",0.00);//已还本金 赋值
        double money= Double.parseDouble(map.get("repayed_money")+"");//已还本金 取值
        map.put("repayed_interests",0.00);//已还利息 赋值
        double money2= Double.parseDouble(map.get("repayed_interests")+"");//已还利息 取值
        map.put("residue_money",a);//剩余应还本金
        map.put("residue_interests",c);//剩余应还利息
        int i=0;
		/*for(int i=1;i<p;i++){*/
			/*Map map2 = new HashMap();*/
        //double interests = ((a*r)/p)/12;//计算出每月应还利息
        double interests =(m*p-a)/p;//计算出每月应还利息
		/*double monthMoney = (m-interests);*///月供-每月偿还利息  月还本金
        double allMoney = All+(money+money2);//已还金额(包括本金和利息)
		map.put("repayed_period", i);//当前期数
        map.put("repay_mmonth",m);//月应还款
        map.put("repay_all_mmonth",m);//月实还款
        map.put("repay_month_money",a/p);//月应还本金
        map.put("repay_month_interest",interests);//月应还利息
        map.put("residue_money",a);//剩余应还本金
        map.put("residue_interests",c);//剩余应还利息
        map.put("repayed_All_money",allMoney);//已还金额(包括本金和利息)
        map.put("repayed_date",SDF.format(new Date()));//还款时间
		/*}*/
        int state = loanDao.insertRepay(map);
        return state;
    }

    /**
     * 查询还款信息表
     * @param GRZH
     * @return
     */
    @Override
    public List<Map> updateGRZHBalance(String GRZH) {
        return loanDao.updateGRZHBalance(GRZH);
    }

    /**
     * 终审通过之后 修改个人账户余额
     * @param map
     * @return
     */
    @Override
    public int updateBalance(Map map) {
        map.put("danlance1",Double.parseDouble(map.get("loan_money")+""));
        return loanDao.updateBalance(map);
    }
}
