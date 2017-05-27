package com.sirosh.app.aspect;


import com.sirosh.app.service.VisitorService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Illya on 5/27/17.
 */

@Aspect
public class StatsAspect {


    @Autowired
    private VisitorService visitorService;


    @Around("execution(* com.sirosh.app.controller.VisitorController.*(String,..))")
    public Object add(ProceedingJoinPoint joinPoint) throws Throwable{

        visitorService.add(new Date(System.currentTimeMillis()),(String) joinPoint.getArgs()[0],joinPoint.getSignature().getName());

        Object res = joinPoint.proceed(joinPoint.getArgs());

        return res;
    }


}
