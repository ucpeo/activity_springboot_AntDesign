package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.*;
import com.ucpeo.activity.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("data")
public class DataAnalysisCtrl {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @GetMapping("act")
    Resp<List<ActStateAnalysis>> actStateAnalysis() {
        return new Resp<>(dataAnalysisService.actStateAnalysis());
    }

    @GetMapping("part")
    Resp<List<PartStateAnalysis>> partStateAnalysis() {
        return new Resp<>(dataAnalysisService.partStateAnalysis());
    }

    @GetMapping("act/user")
    Resp<List<ActStateAnalysis>> actStateAnalysisByMe(@SessionAttribute("loginUser") User user) {
        return new Resp<>(dataAnalysisService.actStateAnalysis(user.getId()));
    }

    @GetMapping("part/user")
    Resp<List<PartStateAnalysis>> partStateAnalysisByMe(@SessionAttribute("loginUser") User user) {
        return new Resp<>(dataAnalysisService.partStateAnalysis(user.getId()));
    }

    @GetMapping("act/user/{id}")
    Resp<List<ActStateAnalysis>> actStateAnalysisByUser(@PathVariable("id") Integer id) {
        return new Resp<>(dataAnalysisService.actStateAnalysis(id));
    }

    @GetMapping("part/user/{id}")
    Resp<List<PartStateAnalysis>> partStateAnalysisByUser(@PathVariable("id") Integer id) {
        return new Resp<>(dataAnalysisService.partStateAnalysis(id));
    }


    @GetMapping("partForm/{id}")
    Resp<List<FormItemAnalysis>> formItemAnalysis(@PathVariable("id") Integer formId) {
        return new Resp<>(dataAnalysisService.formItemAnalysis(formId));
    }

}
