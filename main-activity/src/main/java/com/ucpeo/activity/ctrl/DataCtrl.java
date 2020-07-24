package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.FormItemAnalysis;
import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("data")
public class DataCtrl {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @GetMapping("{id}")
    Resp<List<FormItemAnalysis>> formItemAnalysis(@PathVariable("id") Integer formId) {
        return new Resp<>(dataAnalysisService.formItemAnalysis(formId));
    }
}
