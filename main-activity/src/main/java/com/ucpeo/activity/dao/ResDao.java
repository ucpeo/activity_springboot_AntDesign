package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Res;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResDao extends BaseDao<Res> {
    @Override
    @Insert("INSERT INTO `act`.`res` (`id`, `type`, `value`) \n" +
            "VALUES\n" +
            "  (#{id}, #{type}, #{value} ) ;")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void create(Res res);

    @Override
    @Delete("DELETE from res" +
            "WHERE `id` = #{id};")
    void delete(@Param("id") Integer id);

    @Override
    @Update("UPDATE   `res`  set " +
            "  `id` = #{id},\n" +
            "  `type` = #{type},\n" +
            "  `value` = #{value} ")
    void update(Res res);

    @Override
    @Select("SELECT * from `res` " +
            "WHERE `id` = 'id' ;")
    Res get(@Param("id") Integer id);

    @Override
    @Select("SELECT * from `res` ")
    List<Res> getAll();

    @Insert("<script>" +
            " insert into `res` values " +
            " <foreach collection='res' item='item' index='index'  separator=','>" +
            "  (#{item.id},#{item.type},#{item.value}) "+
            " </foreach>"+
            "</script>")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void batchSave( @Param("res") List<Res> res);

}
