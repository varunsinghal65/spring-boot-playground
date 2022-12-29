package com.varun.playground.spring.aop.services;

import com.varun.playground.spring.aop.models.User;

@FunctionalInterface
public interface UserNameExtractor {
    String extract(User user);
}
