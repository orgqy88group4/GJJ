package com.aaa.gjj.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * className:LogUtil
 * discription: 切面的实现类
 * author:qcm
 * createTime:2018-11-23 18:03
 */
@Component
@Aspect
public class LogUtil {

    @Pointcut(value = "execution(* com.aaa.gjj.service.*.*(..))")
    public void pointCutOne(){ };

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before(value = "pointCutOne()")
    public void beforeSaveLog(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法之前打印-----前置通知-----");
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @AfterReturning(value = "pointCutOne()")
    public void afterReturningSaveLog(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法之后打印=====后置通知====");
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pointCutOne()",throwing = "e")
    public void afterThrowingSaveLog(JoinPoint joinPoint,Exception e){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法时出现了"+e.getClass().getName()+"异常描述"+e.getMessage());
    }


    /**
     * 最终通知
     * @param joinPoint
     */
    @After(value = "pointCutOne()")
    public void afterSaveLog(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法,无论有没有异常都会打印*****最终通知**");
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "pointCutOne()")
    public Object aroundSaveLog(ProceedingJoinPoint proceedingJoinPoint){
        Object proceed = null;
        System.out.println("环绕执行业务之前。。。。");
        try {
            String name = proceedingJoinPoint.getTarget().getClass().getName();
            System.out.println("在调用类"+name+"的"+proceedingJoinPoint.getSignature().getName()+"方法,环绕打印*******");
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕执行业务之后。。。。");
        return proceed;
    }
}
