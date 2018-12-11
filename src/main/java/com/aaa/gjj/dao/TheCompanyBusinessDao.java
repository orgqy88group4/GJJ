package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:TheCompanyBusinessDao
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:03
 */
public interface TheCompanyBusinessDao {
    /**
     * 单位开户 效验公司名称
     * @param map
     * @return
     */
    @Select("select uname,uNetworkCode from tb_unit")
    List<Map> getUnitInfoList(Map map);

    /**
     * 单位开户,点击保存按钮
     * @param map
     * @return
     */
    @Insert("insert into tb_unit values ( null,#{uname},#{uAddress},#{uLegalPerson},#{ULegalType},#{ULegalCard},#{uPayDate},#{uOperator},#{uOpIphone},#{JBRZJLX},#{JBRZJHM},#{UnitBeginDate},#{uNetworkCode},#{GSLX},#{SSHY},#{LSGX},#{JJLX},#{YYZZ},#{uCreater},null)")
    int unitRegister(Map map);
    @Insert("insert into tb_unitaccount (uaID,uaBankName,uaBankNumber,uDepositRatio,uPersonRatio,STYH,uaState,YWBLR,KHRQ,uDepositedPnum,uSealPnum,uARemain,uACancellDate,uacReason,uaPayEndDate,uaOweMonery,uaOweMonths,uaTebalance,DWZH,uid) values " +
            "( null,#{uaBankName},#{uaBankNumber},#{uDepositRatio},#{uPersonRatio},#{STYH},1,#{YWBLR},#{KHRQ},1,0,0,null,null,null,0,0,null,concat(DATE_FORMAT(now(),'%Y%m'),lpad(floor(RAND()*100000),10,0)),#{uid})")
    int unitAccount(Map map, int uid);
}
