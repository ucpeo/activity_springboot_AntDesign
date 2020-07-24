package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.FormItemAnalysis;

import java.util.List;

public interface DataAnalysisService {
    List<FormItemAnalysis> formItemAnalysis(Integer formId);
}

