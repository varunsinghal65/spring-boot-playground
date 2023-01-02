package com.varun.playground.spring.aop.services;

import com.varun.playground.spring.aop.models.User;

public class Extractors {

    private Extractors(){}

    public static final UserNameExtractor userNameExtractor = User::getUserName;

}
