package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Option;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OptionDao extends BaseDao<Option> {
    @Override
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into `option`(\n" +
            "  `id`,\n" +
            "  `value`,\n" +
            "  `right`,\n" +
            "  `form_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{value},\n" +
            "    #{right},\n" +
            "    #{actForm.id}\n" +
            "  ) ;\n")
    void create(Option option);

    @Override
    @Delete("delete from `option` where id = #{value}")
    void delete(Integer id);

    @Override
    @Update("UPDATE \n" +
            " `option` \n" +
            "SET\n" +
            "  `id` = #{id},\n" +
            "  `value` = #{value},\n" +
            "  `right` = #{right},\n" +
            "  `form_id` = #{actForm.id} \n" +
            "WHERE `id` = #{id} ;\n")
    void update(Option option);

    @Override
    @Select("select * from `option` where id = #{value}")
    @Results(id = "option", value = @Result(column = "form_id", property = "actForm", one = @One(select = "com.ucpeo.activity.dao.ActFormDao.get", fetchType = FetchType.LAZY)))
    Option get(Integer id);


    @Override
    @Select("select * from `option`")
    @ResultMap("option")
    List<Option> getAll();

    @Select("select * from `option` where form_id = #{value}")
    @ResultMap("option")
    List<Option> getFormOptions(Integer formId);

    @Select("select * from `option` where id in (select `option_id` from `option_input` where part_form_id=#{value})")
    @ResultMap("option")
    List<Option> getInputOptions(Integer partFromId);

    @Insert("<script>" +
            " insert into `option_input` values " +
            " <foreach collection='optionList' item='it' index='index'  separator=','> " +
            " (#{it.id},#{pid}) " +
            " </foreach>" +
            "</script>")
    void saveOptionInput(@Param("pid") Integer partFormId, @Param("optionList") List<Option> optionList);

    @Insert("<script>" +
            " insert into `option` values " +
            " <foreach collection='optionList' item='it' index='index'  separator=','> " +
            " (#{it.id},#{it.value},#{it.right},#{formid}) " +
            " </foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "optionList.id")
    void batchSaveOption(@Param("formid") Integer actFormId, @Param("optionList") List<Option> optionList);

    @Delete("delete from `option_input` where option_id = @{opt} and part_from_id = #{partakeFormId}")
    void deleteOptionInput(@Param("opt") Integer optionId,@Param("partakeFormId") Integer partakeFormId);

}
