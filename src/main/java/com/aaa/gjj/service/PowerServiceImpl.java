package com.aaa.gjj.service;

import com.aaa.gjj.dao.PowerDao;
import com.aaa.gjj.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * className:PowerServiceImpl
 * discription:后台树
 * author:zz
 * createTime:2018-12-11 09:38
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDao powerDao;

    @Override
    public List<Node> getList() {
        List<Node> list = powerDao.getList();
        //拼装后的临时集合，用于返回数据
        List<Node> tempList = new ArrayList<Node>();
        if(list!=null&&list.size()>0){
            for (Node node : list) {
                //找出父节点为0的一级节点
                 if(node.getPid()==0){
                     tempList.add(node);
                     //调用递归方法，找当前节点的子节点
                     bindChildren(node,list);
                 }
            }
        }
        return tempList;
    }

    /**
     * 递归绑定子节点
     * @param curNode
     * @param list
     */
    private void bindChildren(Node curNode, List<Node> list){
         for(Node node : list) {
            //如果当前节点的id和循环节点的父ID相等，说明是当前节点的孩子
            if(curNode.getId()==node.getPid()){
                //取出当前节点的孩子集合
                List<Node> children = curNode.getChildren();
                //如果是没有孩子，孩子集合第一次取出是空的
                if(children==null){
                    children = new ArrayList<Node>();
                }
                //添加孩子
                children.add(node);
                //设置当前孩子集合
                curNode.setChildren(children);
                //自己调用自己，找孩子
                bindChildren(node,list);
            }
        }
    }
}
