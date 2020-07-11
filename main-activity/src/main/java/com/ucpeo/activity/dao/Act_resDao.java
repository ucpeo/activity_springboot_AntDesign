package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Res;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Act_resDao {
    @Select("select * from `res` where id in (select res_id from `act_res` where  `act_id`=#{value} ) ")
    List<Res> actRes(Integer act_id);

    @Insert("insert into `act_res` values(#{act.id},#{res.id})")
    void save(@Param("act") Act act,@Param("res") Res res);

    @Insert("<script>" +
            " insert into `act_res` values " +
            " <foreach collection='res' item='item' index='index'  separator=','>" +
            "  (#{act.id},#{item.id}) "+
            " </foreach>"+
            "</script>")
    void batchSave(@Param("act") Act act,@Param("res") List<Res> res);



}
