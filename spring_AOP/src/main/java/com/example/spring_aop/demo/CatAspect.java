package com.example.spring_aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class CatAspect {

    @Pointcut("@annotation(com.example.spring_aop.demo.CatAopTest)")
    public void pointCut() {

    }

    /**
     * 前置通知
     *
     * @param jp 拦截切点
     */
    @Before("pointCut()")
    public void deBefore(JoinPoint jp) {
        //接收请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //记录请求内容
        log.info("Before URL: {}", request.getRequestURL().toString());
        log.info("Before HTTP_MOTHOD: {}", request.getMethod());
        log.info("Before IP: {}", request.getRemoteAddr());
        log.info("Before CLASS : {}, METHOD : {}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        log.info("Before ARGS : {}", jp.getArgs());
    }


    /**
     * 后置处理
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) {
        log.info("AfterReturnin result : {}", ret);
    }

    /**
     * 后置异常通知
     * @param jp
     */
    @AfterThrowing("pointCut()")
    public void throwss(JoinPoint jp) {
        log.info("AfterThrowing 方法异常：{}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * 后置最终通知
     * final 增强，不管是否有异常都会执行
     * @param jp
     */
    @After("pointCut()")
    public void after(JoinPoint jp){
        log.info("after 方法最后执行 final ：{}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @Around("pointCut()")
    public Object arround(ProceedingJoinPoint pjp) {
        log.info("方法环绕start.....");
        try {
            //执行切点的方法（即真正的业务逻辑）
            Object o =  pjp.proceed();
            log.info("arround 方法环绕proceed，结果是 : {}" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
