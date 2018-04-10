package com.terwergreen.middle.user.service;

import java.util.Map;

@SuppressWarnings({ "rawtypes" })
public interface LoginService {
    Map login(String account, String password);
}
