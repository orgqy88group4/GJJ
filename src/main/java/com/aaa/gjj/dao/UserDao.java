package com.aaa.gjj.dao;

import com.aaa.gjj.entity.Person;
import com.aaa.gjj.entity.User;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:fhm
 * createTime:2018-12-06 21:11
 */
public interface UserDao {
    /**
     * 根据用户名找用户
     * @param ename
     * @return
     */
    @Select("select empno,ename,job from tbq_user4 where ename=#{ename}")
    List<User> getUserByuserName(String ename);

//    /**
//     * 通过id找用户
//     * @return
//     */
//    @Select("select empno,ename,job,perms from tbq_user4 where empno=#{empNo}")
//    List<User> getUserById(Integer empNo);

    /**
     * 根据用户名找用户
     * @param userName
     * @return
     */
    @Select("select * from tb_account where userName=#{userName}")
    List<Map> selByUserName(String userName);

    /**
     * 通过id找用户
     * @return
     */
    @Select("select empno,ename,job,perms from tbq_user4 where empno=#{empNo}")
    List<User> getUserById(Integer empNo);






    //1211

    /**
     * 查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @param map
     * @return
     */
    @Select("select transfer_id,pname,idNumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,audit_state,id from tb_transfer_audit where id = #{id}")
    List<Map> submitVerify1(Map map);

    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行
     * @param id
     * @return
     */
    @Select("select a.uid,b.uid,b.STYH from tb_unit a,tb_unitaccount b  where a.uid=b.uid and a.uid=#{id}")
    List<Map> Trustee1(int id);

    /**
     * 根据用户ID查询
     * @param id
     * @return
     */
    @Select("select a.tb_pName,a.tb_pid,b.pid,b.uaid,c.uid,c.uname,d.uid,d.uaBankName from  tb_person_info a,tb_paccountutil b,tb_unit c,tb_unitaccount d where a.tb_pid=b.pid and b.uaid=c.uid and c.uid=d.uid and tb_pName= #{id}")
    List<Map> UserIDSelect1(String id);

    /**
     * 用户列表
     * @return
     */
    @Select("<script>select pname,idNumber,balance,state from tb_transfer_audit" +
            "<if test=\"pname!=null and pname!=''\"> where pname like concat('%',#{pname},'%')</if>" +
            "limit #{start},#{end}</script>")
    List<Map> UserSelect1(Map map);

    /**
     * 获取用户列表总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) as cnt from tb_transfer_audit" +
            "<if test=\"pname!=null and pname!=''\"> where pname like concat('%',#{pname},'%')</if>" +
            "</script>")
    List<Map> UserCount12(Map map);

    /**
     *获取分页数据
     * @param map
     * @return
     */
    @Select("<script>select tb_pName,tb_idNUmber,tb_pid,GRZH,dalance,peraccState from tb_person_info a ,tb_paccountutil b  where a.tb_pid = b.pid and b.peraccState=1" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            " limit #{start},#{end}</script>" )
    List<Map> getPage1(Map map);

    /**
     * 查询分页数据的总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) as cnt from tb_person_info a ,tb_paccountutil b where a.tb_pid = b.pid and b.peraccState=1" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            "</script>")
    List<Map> getPageCount1(Map map);

    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @param id
     * @return
     */
    @Select("select tb_pName,tb_idNUmber,baseNummber,tb_pid,GRZH,dalance,peraccState from tb_person_info a ,tb_paccountutil b where a.tb_pid = b.pid  and tb_pid= #{id}")
    List<Map> shift(int id);
    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @param id
     * @return
     */
    @Select("select tb_pName,tb_idNUmber,baseNummber,tb_pid,GRZH,dalance,peraccState from tb_person_info a ,tb_paccountutil b where a.tb_pid = b.pid  and tb_pid= #{id}")
    List<Map> shift1(int id);

    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行   添加到审核表中
     * @param id
     * @return
     */
    @Select("select a.uid,b.uid,a.uname from tb_unit a,tb_unitaccount b  where a.uid=b.uid and a.uid=#{id}")
    List<Map> uname(int id);
    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行   添加到审核表中
     * @param id
     * @return
     */
    @Select("select a.uid,b.uid,a.uname from tb_unit a,tb_unitaccount b  where a.uid=b.uid and a.uid=#{id}")
    List<Map> uname1(int id);

    /**
     * 把获取到的人员信息存入审核表中
     * @param map
     * @return
     */
    @Insert("insert into tb_transfer_audit(pname,idNumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,audit_state,baseNummber,id)values(#{pname},#{idNumber},#{balance},#{state},#{transfer_out_unit},#{transfer_into_unit},#{auditor},#{person_account},#{transfer_reason},#{operator},#{submit_time},#{audit_state},#{baseNummber},#{id})")
    int addShift1(Map map);

    /**
     * 公司动态下拉框
     * @return
     */
    @Select("select a.uid,uname from tb_unit a,tb_unitaccount b where b.uid=a.uid and uaState = 1")
    List<Map> select();
}
