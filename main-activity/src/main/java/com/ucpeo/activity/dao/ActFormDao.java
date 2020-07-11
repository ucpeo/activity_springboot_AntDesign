package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.ActForm;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ActFormDao extends  BaseDao<ActForm>{
    @Override
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("INSERT INTO `act_form` (`id`, `name`, `type`, `act_id`) \n" +
            "VALUES\n" +
            "  (#{id}, #{name}, #{type}, #{act.id}) ;\n")
    void create(ActForm actFrom);

    @Override
    @Delete("delete from `act_form` where id = #{value}")
    void delete(Integer id);

    @Override
    @Update("UPDATE \n" +
            "  `act_form` \n" +
            "SET\n" +
            "  `id` = #{id},\n" +
            "  `name` = #{name},\n" +
            "  `type` = #{type},\n" +
            "  `act_id` = #{act.id}' \n" +
            "WHERE `id` = #{id} ;\n" +
            "\n")
    void update(ActForm actFrom);

    @Override
    @Select("select * from `act_form`  where id = #{value}")
    @Results(id = "actForm",value = {
            @Result(column = "act_id",property = "act",one = @One(select = "com.ucpeo.activity.dao.ActDao.get",fetchType = FetchType.LAZY)),
            @Result(column = "id",property = "optionList",many = @Many(select = "com.ucpeo.activity.dao.OptionDao.getFormOptions",fetchType = FetchType.LAZY)),
            @Result(column = "id",property = "id",id = true)
    })
    ActForm get(Integer id);

    @Override
    @Select("select * from `act_form`")
    @ResultMap("actForm")
    List<ActForm> getAll();

    @Select("select * from `act_form` where act_id = #{value}")
    @ResultMap("actForm")
    List<ActForm> actActForm(Integer actId);
}
