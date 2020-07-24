package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.User;

import java.util.Map;

public interface UserActDataService {
  Map<String,Integer> getUserActDataInfo(User user);
}

