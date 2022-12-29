package com.varun.playground.spring.aop.services;

public class Extractors {

    public static final UserNameExtractor userNameExtractor = (user) -> user.getUserName();

}
