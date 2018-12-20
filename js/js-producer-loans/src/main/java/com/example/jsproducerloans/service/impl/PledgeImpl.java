package com.example.jsproducerloans.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jsproducerloans.controllerpojo.LoansTransactionCon;
import com.example.jsproducerloans.dao.LoansTransactionDao;
import com.example.jsproducerloans.dao.LoantypeDao;
import com.example.jsproducerloans.dao.RepaymentTypeDao;
import com.example.jsproducerloans.pojo.LoansTransaction;
import com.example.jsproducerloans.pojo.RepaymentType;
import com.example.jsproducerloans.service.Pledge;
import com.example.jsproducerloans.util.Result;
import com.example.jsproducerloans.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PledgeImpl implements Pledge {

    @Autowired
    private LoantypeDao loantypeDao;
    @Autowired
    private LoansTransactionDao loansTransactionDao;
    @Autowired
    private RepaymentTypeDao repaymentTypeDao;

    /**
     * 全部贷款种类
     *
     * @return 贷款种类集合
     */
    @Override
    public Result allLoantype() {
        return ResultUtil.success(JSON.toJSON(loantypeDao.selectAll()));
    }

    /**
     * 查看指定用户ID的所有贷款
     *
     * @return 所有贷款集合
     */
    @Override
    public Result allLoansTransactionByUid(Integer uid) {
        Example example = new Example(LoansTransaction.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("liuid", uid);
        criteria.andEqualTo("listate", 1);
        List<LoansTransaction> loansTransactions = loansTransactionDao.selectByExample(example);
        List<LoansTransactionCon> loansTransactionCons = new ArrayList<>();
        for (int i = 0; i < loansTransactions.size(); i++) {
            LoansTransactionCon loansTransactionCon = new LoansTransactionCon();
            loansTransactionCon.setLiid(loansTransactions.get(i).getLiid());
            loansTransactionCon.setLiuid(loansTransactions.get(i).getLiuid());
            Example example1 = new Example(RepaymentType.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("rtid", loansTransactions.get(i).getLitype());
            RepaymentType repaymentType = repaymentTypeDao.selectOneByExample(example1);
            loansTransactionCon.setLitype(repaymentType.getRtname());
            loansTransactionCon.setLinumberofperiods(loansTransactions.get(i).getLinumberofperiods());
            loansTransactionCon.setLinumber(loansTransactions.get(i).getLinumber());
            loansTransactionCon.setLidate(loansTransactions.get(i).getLidate());
            loansTransactionCons.add(loansTransactionCon);
            example1 = null;
            criteria1 = null;
        }
        return ResultUtil.success(JSON.toJSON(loansTransactionCons));
    }

    /**
     * 生成订单
     *
     * @param loansTransaction 订单对象
     * @return Result
     */
    @Override
    public Result addLoansTransaction(LoansTransaction loansTransaction) {
        loansTransaction.setListate(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        loansTransaction.setLidate(simpleDateFormat.format(new Date()));
        int count = loansTransactionDao.insert(loansTransaction);
        if (count == 1) {
            return ResultUtil.success(JSON.toJSON(new String("生成订单成功")));
        } else {
            return ResultUtil.success(JSON.toJSON(new String("生成订单失败")));
        }
    }

    /**
     * 根据订单ID修改订单状态
     *
     * @param lid   订单ID
     * @param state 订单状态
     * @return
     */
    @Override
    public Result updateLoansTransactionToState(Integer lid, Integer state) {
        if (state > 1 || state < 0) {
            return ResultUtil.success(JSON.toJSON(new String("状态码有误")));
        }
        Example example = new Example(LoansTransaction.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("liid", lid);
        LoansTransaction loansTransaction = loansTransactionDao.selectOneByExample(example);
        if (loansTransaction == null) {
            return ResultUtil.success(JSON.toJSON(new String("订单不存在")));
        }
        loansTransaction.setListate(state);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        loansTransaction.setLidate(simpleDateFormat.format(new Date()));
        int count = loansTransactionDao.updateByExampleSelective(loansTransaction, example);
        if (count == 1) {
            return ResultUtil.success(JSON.toJSON(new String("修改订单状态成功")));
        } else {
            return ResultUtil.success(JSON.toJSON(new String("修改订单状态失败")));
        }
    }
}