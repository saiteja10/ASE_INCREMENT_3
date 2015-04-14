package com.ase.api.aop;

import com.ase.bean.SuperDTO;
import com.ase.hibernate.HibernateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vivek
 * Date: 11/19/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
public class TransactionAspect {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionAspect.class.getName());

    @Around(value = "@annotation(com.ase.api.aop.MyTransaction)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        try {
            // continue on the intercepted method
            Object returnObject = joinPoint.proceed();
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            return returnObject;
        } catch (ConstraintViolationException e) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            SuperDTO superDTO = handleException(methodSignature, "Duplicate Entry");
            logger.error(e.getMessage(), e);
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            return superDTO;
        } catch (Exception e) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            SuperDTO superDTO = handleException(methodSignature, e.getMessage());
            logger.error(e.getMessage(), e);
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            return superDTO;
        }
    }

    private SuperDTO handleException(MethodSignature methodSignature, String message) {
        SuperDTO superDTO = null;
        try {
            Class aClass = methodSignature.getReturnType();
            superDTO = (SuperDTO) aClass.newInstance();
            superDTO.setSuccess(false);
            superDTO.setMessage(message);
        } catch (InstantiationException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (ClassCastException e) {
            return null;
        }
        return superDTO;
    }
}
