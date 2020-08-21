package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.ActStateAnalysis;
import com.ucpeo.activity.bean.FormItemAnalysis;
import com.ucpeo.activity.bean.PartStateAnalysis;
import com.ucpeo.activity.dao.DataAnalysisDao;

import java.util.List;

public interface DataAnalysisService {
    List<FormItemAnalysis> formItemAnalysis(Integer formId);

    List<PartStateAnalysis> partStateAnalysis(Integer userId);

    List<ActStateAnalysis> actStateAnalysis(Integer userId);
    List<PartStateAnalysis> partStateAnalysis();

    List<ActStateAnalysis> actStateAnalysis();
}

