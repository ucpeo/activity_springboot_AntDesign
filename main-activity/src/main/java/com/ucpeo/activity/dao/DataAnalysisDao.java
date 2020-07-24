package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.FormItemAnalysis;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataAnalysisDao {
    @Results({
            @Result(column = "optionid", property = "optionId"),
            @Result(column = "selected", property = "selected")
    })
    @Select("SELECT option_id optionid,COUNT(1) selected FROM `option_input`  WHERE option_id IN(SELECT id FROM  `option` WHERE form_id = #{value}) GROUP BY option_id")
    List<FormItemAnalysis> formItemAnalysis(Integer formId);
}


