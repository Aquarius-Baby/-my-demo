package com.cmy.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionTest {


    @Autowired
    private PlatformTransactionManager txManager;

    // 编程性事务

    /**
     * PlatformTransactionManager 事务管理器
     * TransactionDefinition 事务的一些基础信息，如超时时间、隔离级别、传播属性等
     * TransactionStatus 事务的一些状态信息，如是否一个新的事务、是否已被标记为回滚
     *
     * @param args
     */
    public void test(String[] args) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；获取事务状态后，Spring根据传播行为来决定如何开启事务
        TransactionStatus status = txManager.getTransaction(def);
        try {
            txManager.commit(status);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            txManager.rollback(status);
        }
    }

}
