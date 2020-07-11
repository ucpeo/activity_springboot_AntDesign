package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.PartForm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PartFormDao extends  BaseDao<PartForm>{
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("INSERT INTO `part_form` (\n" +
            "  `id`,\n" +
            "  `partake_id`,\n" +
            "  `value`,\n" +
            "  `act_form_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{partake.id},\n" +
            "    #{value},\n" +
            "    #{actForm.id}\n" +
            "  ) ;\n")
    void create(PartForm partForm);

    @Delete("delete from `part_form` where id = #{value}")
    void delete(Integer id);

    @Update("UPDATE \n" +
            " `part_form` \n" +
            "SET\n" +
            "  `id` = #{id},\n" +
            "  `partake_id` = #{partake.id},\n" +
            "  `value` = #{value},\n" +
            "  `act_form_id` = #{actForm.id} \n" +
            "WHERE `id` = #{id} ;\n")
    void update(PartForm partForm);

    @Select("select * from `part_form` where id = #{value}")
    @Results(id = "partForm",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "partake_id",property = "partake",one = @One(fetchType = FetchType.LAZY,select ="com.ucpeo.activity.dao.PartakeDao.get")),
            @Result(column = "act_form_id",property = "actForm",one = @One(fetchType = FetchType.LAZY,select ="com.ucpeo.activity.dao.ActFormDao.get")),
            @Result(column = "id",property = "optionList",many = @Many(select ="com.ucpeo.activity.dao.OptionDao.getInputOptions",fetchType = FetchType.LAZY)),

    })
    PartForm get(Integer id);

    @Select("select * from `part_form`")
    @ResultMap("partForm")
    List<PartForm> getAll();

    @Select("select * from `part_form` where partake_id = #{value}")
    @ResultMap("partForm")
    List<PartForm> partFormByPart(Integer id);






}
