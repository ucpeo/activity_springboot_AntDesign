package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.FormItemAnalysis;
import com.ucpeo.activity.dao.DataAnalysisDao;
import com.ucpeo.activity.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
    @Resource
    DataAnalysisDao dataAnalysisDao;
    @Override
    public List<FormItemAnalysis> formItemAnalysis(Integer formId) {
        return dataAnalysisDao.formItemAnalysis(formId);
    }
}
