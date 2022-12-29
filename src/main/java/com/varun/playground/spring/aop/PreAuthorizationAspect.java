package com.varun.playground.spring.aop;

import com.varun.playground.spring.aop.services.AuthorizationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.stream.IntStream;

@Component
@Aspect
public class PreAuthorizationAspect {

    private final ExpressionParser parser;
    private AuthorizationService authorizationService;

    PreAuthorizationAspect(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        parser = new SpelExpressionParser();
    }

    @Before("@annotation(com.varun.playground.spring.aop.PreAuthorize)")
    public void preAuthorizeAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PreAuthorize preAuthorizeAnnotationInstance = method.getAnnotation(PreAuthorize.class);
        //get the evaluation context needed to exmaine the spel
        Object[] methodArgsValues = joinPoint.getArgs();
        String[] methodArgsNames = methodSignature.getParameterNames();
        EvaluationContext evaluationContext = getSpelExprEvaluationContext(methodArgsValues, methodArgsNames);
        //parse the expression
        String parsedUserName = (String) parser.parseExpression(preAuthorizeAnnotationInstance.userName()).getValue(evaluationContext, Object.class);
        String parsedUserAction = (String) parser.parseExpression(preAuthorizeAnnotationInstance.userAction()).getValue(evaluationContext, Object.class);
        //print
        System.out.println("Parsed user name is: " + parsedUserName);
        System.out.println("Parsed user action is: " + parsedUserAction);
        if (!authorizationService.isAuthorized(parsedUserName, parsedUserAction)) {
            throw new RuntimeException("Unauthorized");
        }
    }

    private static EvaluationContext getSpelExprEvaluationContext(Object[] methodArgsValues, String[] methodArgsNames) {
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        IntStream.range(0, methodArgsNames.length).forEach(idx -> {
            evaluationContext.setVariable(methodArgsNames[idx], methodArgsValues[idx]);
        });
        return evaluationContext;
    }
}
