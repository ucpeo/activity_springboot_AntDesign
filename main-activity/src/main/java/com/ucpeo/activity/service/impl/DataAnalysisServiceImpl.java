package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.ActStateAnalysis;
import com.ucpeo.activity.bean.FormItemAnalysis;
import com.ucpeo.activity.bean.PartStateAnalysis;
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

    @Override
    public List<PartStateAnalysis> partStateAnalysis(Integer userId) {
        return dataAnalysisDao.partStateAnalysis(userId);
    }

    @Override
    public List<ActStateAnalysis> actStateAnalysis(Integer userId) {
        return dataAnalysisDao.actStateAnalysis(userId);
    }
    @Override
    public List<PartStateAnalysis> partStateAnalysis() {
        return dataAnalysisDao.partStateAnalysis(null);
    }

    @Override
    public List<ActStateAnalysis> actStateAnalysis() {
        return dataAnalysisDao.actStateAnalysis(null);
    }
}
