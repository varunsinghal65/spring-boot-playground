package com.varun.playground.spring.aop.services;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public boolean isAuthorized(final String userName, final String userAction) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userAction)) return false;
        if (userName.equals("varunsinghal")) {
            return true;
        } else {
            return false;
        }
    }
}
