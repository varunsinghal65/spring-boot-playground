package com.varun.playground.springAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
@Aspect
public class LoggingAspect {

    @Before("@annotation(Log)")
    public void logMethodsAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log logAnnotationInstance = method.getAnnotation(Log.class);
        //create evaluation context
        Object[] methodArgsValues = joinPoint.getArgs();
        String[] methodArgsNames = methodSignature.getParameterNames();
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        IntStream.range(0, methodArgsNames.length).forEach(idx -> {
            evaluationContext.setVariable(methodArgsNames[idx], methodArgsValues[idx]);
        });
        //parse the expression
        ExpressionParser parser = new SpelExpressionParser();
        String parsedParam1 = (String) parser.parseExpression(logAnnotationInstance.param1()).getValue(evaluationContext, Object.class);
        String parsedParam2 = (String) parser.parseExpression(logAnnotationInstance.param2()).getValue(evaluationContext, Object.class);
        System.out.println("Parsed param1 is: " + parsedParam1);
        System.out.println("Parsed param2 is: " + parsedParam2);
    }
}
