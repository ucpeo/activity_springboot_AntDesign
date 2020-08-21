package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.ActStateAnalysis;
import com.ucpeo.activity.bean.FormItemAnalysis;
import com.ucpeo.activity.bean.PartStateAnalysis;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataAnalysisDao {
    @Results({
            @Result(column = "optionid", property = "optionId"),
            @Result(column = "selected", property = "selected")
    })
//    @Select("SELECT option_id optionid,COUNT(1) selected FROM `option_input`  WHERE `part_form_id` is not null and option_id IN(SELECT id FROM  `option` WHERE form_id = #{value}) GROUP BY option_id")
 @Select("SELECT \n" +
         "  option_id optionid,\n" +
         "  COUNT(1) selected \n" +
         "FROM\n" +
         "  option_input \n" +
         "WHERE part_form_id IN \n" +
         "  (SELECT \n" +
         "    id \n" +
         "  FROM\n" +
         "    part_form \n" +
         "  WHERE \n" +
         "    act_form_id = #{value}\n" +
         "  AND partake_id IN \n" +
         "    (SELECT \n" +
         "      id \n" +
         "    FROM\n" +
         "      partake \n" +
         "    WHERE  state = 7)) \n" +
         "GROUP BY option_id ")
    List<FormItemAnalysis> formItemAnalysis(Integer formId);

    @Select("<script> " +
            "SELECT state,COUNT(id) 'count' FROM partake" +
            "<if test='value!=null'>" +
            " WHERE " +
            " partake.`user_id`=#{value} and " +
            "</if>" +
            " activity_id IS NOT NULL" +
            " GROUP BY state " +
            "</script>")
    List<PartStateAnalysis> partStateAnalysis(Integer userId);
    @Select("<script> " +
            "SELECT state,COUNT(id) 'count' FROM act" +
            "<if test='value!=null'>" +
            "where  act.`create_user`=#{value}  " +
            "</if>" +
            " GROUP BY state " +
            "</script>")
    List<ActStateAnalysis> actStateAnalysis(Integer userId);

}


