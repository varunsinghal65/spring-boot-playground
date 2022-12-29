package com.varun.playground.spring.aop.services;

public class Extractors {

    private Extractors(){}

    public static final UserNameExtractor userNameExtractor = (user) -> user.getUserName();

}
