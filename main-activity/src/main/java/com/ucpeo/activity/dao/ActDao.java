package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Example;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActDao extends BaseDao<Act> {
    @Override
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("INSERT INTO `act` (\n" +
            "  `id`,\n" +
            "  `name`,\n" +
            "  `create_time`,\n" +
            "  `describe`,\n" +
            "  `check_level`,\n" +
            "  `state`,\n" +
            "  `participants`,\n" +
            "  `recruited`,\n" +
            "  `type`,\n" +
            "  `end_time`,\n" +
            "  `en_res`,\n" +
            "  `en_form`,\n" +
            "  `create_user`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{name},\n" +
            "    #{createTime},\n" +
            "    #{describe},\n" +
            "    #{checkLevel},\n" +
            "    #{state},\n" +
            "    #{participants},\n" +
            "    #{recruited},\n" +
            "    #{type},\n" +
            "    #{endTime},\n" +
            "    #{enRes},\n" +
            "    #{enForm},\n" +
            "    #{createUser.id}\n" +
            "  ) ;\n")
    void create(Act act);

    @Override
    @Delete("delete from `act`  where id = #{value}")
    void delete(Integer id);

    @Override
    @Update("UPDATE \n" +
            " `act` \n" +
            "SET\n" +
            "  `id` = #{id},\n" +
            "  `name` = #{name},\n" +
            "  `create_time` = #{createTime},\n" +
            "  `describe` = #{describe},\n" +
            "  `check_level` = #{checkLevel},\n" +
            "  `state` = #{state},\n" +
            "  `participants` = #{participants},\n" +
            "  `recruited` = #{recruited},\n" +
            "  `type` = #{type},\n" +
            "  `end_time` = #{endTime},\n" +
            "  `en_res` = #{enRes},\n" +
            "  `en_form` = #{enForm} \n" +
            "WHERE `id` = #{id} ;\n" +
            "\n")
    void update(Act act);

    @Override
    @Select("select * from `act` where id = #{value}")
    @Results(id = "act", value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "end_time",property = "endTime"),
            @Result(column = "check_level",property = "checkLevel"),
            @Result(column = "create_user", property = "createUser", one = @One(select = "com.ucpeo.activity.dao.UserDao.get", fetchType = FetchType.LAZY)),
            @Result(column = "en_res", property = "enRes"),
            @Result(column = "en_form", property = "enForm"),
            @Result(column = "id", property = "resList", many = @Many(select = "com.ucpeo.activity.dao.Act_resDao.actRes", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "actFormList", many = @Many(select = "com.ucpeo.activity.dao.ActFormDao.actActForm", fetchType = FetchType.LAZY))
    })
    Act get(Integer id);

    @Override
    @Select("select * from `act`")
    @ResultMap("act")
    List<Act> getAll();

    @Select("select * from `act` where create_user=#{value}")
    @ResultMap("act")
    List<Act> getActsByUser(Integer userId);

    @Select("select count(1) from `partake` where activity_id = #{act} and user_id=#{user}")
    Boolean checkUserTake(@Param("act") Integer actId,@Param("user") Integer userId);


    @ResultMap("act")
    @Select("<script> select * from `act` where true  and  " +
            "<if test='e.id!=null'>  `id` =#{e.id}  AND </if> " +
            "<if test='e.name!=null'>   CONCAT(`name`,`describe`) like #{e.name}  AND </if> " +
            "<if test='e.checkLevel!=null'>  `check_level`=#{e.checkLevel}  AND </if> " +
            "<if test='e.state!=null'>        `state`=#{e.state}  AND </if> " +
            "<if test='e.participants!=null'>  `participants`=#{e.participants}  AND </if> " +
            "<if test='e.recruited!=null'>  `recruited`=#{e.recruited}  AND </if> " +
            "<if test='e.type!=null'>  `type`=#{e.type}  AND </if> " +
            "<if test='e.enRes!=null'>  `en_res`=#{e.enRes}  AND </if> " +
            "<if test='e.enForm!=null'>  `en_form`=#{e.enForm}  AND </if> " +
            "<if test='e.createUser!=null'>  `create_user`=#{e.createUser.id}  AND </if> " +
            " true " +
            "<if test='e._start!=null'> limit #{e._start} , #{e._end}  </if> " +
            " </script>")
    List<Act> listByExample(@Param("e") Example<Act> example);

   @Select("<script> select count(1) from `act` where true  and  " +
           "<if test='e.id!=null'>  `id`=#{e.id}  AND </if> " +
           "<if test='e.name!=null'>   CONCAT(`name`,`describe`) like #{e.name} AND </if> " +
           "<if test='e.checkLevel!=null'>  `check_level`=#{e.checkLevel}  AND </if> " +
           "<if test='e.state!=null'>        `state`=#{e.state}  AND </if> " +
           "<if test='e.participants!=null'>  `participants`=#{e.participants}  AND </if> " +
           "<if test='e.recruited!=null'>  `recruited`=#{e.recruited}  AND </if> " +
           "<if test='e.type!=null'>        `type`=#{e.type}  AND </if> " +
           "<if test='e.enRes!=null'>       `en_res`=#{e.enRes}  AND </if> " +
           "<if test='e.enForm!=null'>      `en_form`=#{e.enForm}  AND </if> " +
           "<if test='e.createUser!=null'>  `create_user`=#{e.createUser.id}  AND </if> " +
           " true " +
           " </script>")
    Integer count(@Param("e") Example<Act> example);
}
