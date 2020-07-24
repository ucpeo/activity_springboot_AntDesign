package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Partake;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PartakeDao extends BaseDao<Partake> {
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO `partake` (\n" +
            "  `id`,\n" +
            "  `activity_id`,\n" +
            "  `state`,\n" +
            "  `user_id`,\n" +
            "  `submit_time`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{act.id},\n" +
            "    #{state},\n" +
            "    #{user.id},\n" +
            "    #{submitTime}\n" +
            "  )"
    )
    void create(Partake partake);

    @Delete("delete  from `partake` where id = #{value}")
    void delete(Integer id);


    @Update("UPDATE \n" +
            " `partake` \n" +
            "SET\n" +
            "  `id` = #{id},\n" +
            "  `activity_id` = #{act.id},\n" +
            "  `state` = #{state},\n" +
            "  `user_id` = #{user.id},\n" +
            "  `submit_time` = #{submitTime} \n" +
            "WHERE `id` = #{id} ;\n" +
            "\n")
    void update(Partake partake);


    @Select("select * from `partake` where id =#{value}")
    @Results(id = "partake", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "submit_time", property = "submitTime"),
            @Result(column = "user_id", property = "user", one = @One(select = "com.ucpeo.activity.dao.UserDao.get", fetchType = FetchType.LAZY)),
            @Result(column = "activity_id", property = "act", one = @One(select = "com.ucpeo.activity.dao.ActDao.get", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "partFormInputList", many = @Many(select = "com.ucpeo.activity.dao.PartFormDao.partFormByPart", fetchType = FetchType.LAZY)),
    })
    Partake get(Integer id);

    @Select("select * from `partake`")
    @ResultMap("partake")
    List<Partake> getAll();


    @Select("select * from `partake` where user_id = #{value}")
    @ResultMap("partake")
    List<Partake> getPartsByUser(Integer userId);

    @Select("select * from `partake` where activity_id=#{act} and `state`=#{state}")
    @ResultMap("partake")
    List<Partake> getActPartByState(@Param("act") Integer actId, @Param("state") Integer state);

    @Select("select count(1) from `partake`  where  `state`=#{partState} and activity_id=#{actId} ")
    Integer getByStateCount(@Param("actId") Integer actId, @Param("partState") Integer partState);

    @ResultMap("partake")
    @Select("<script> " +
            "select * from `partake` where true and " +
            "<if test='e.state!=null'>" +
            " `state` = #{e.state}  and " +
            "</if>" +
            "<if test='e.act!=null'>" +
            " `activity_id`=#{e.act.id}  and " +
            "</if>" +
            "<if test='e.user!=null'>" +
            " `user_id`=#{e.user.id}  and" +
            "</if>" +
            "  `activity_id` is not null " +
            " <if test='e._start != null'>" +
            " limit #{e._start} , #{e._end} " +
            "</if>" +
            "</script>")
    List<Partake> listByExample(@Param("e") Example example);

    @Select("<script> " +
            "select count(1) from `partake` where 1=1 and " +
            "<if test='e.state!=null'>" +
            " `state`=#{e.state}  and " +
            "</if>" +
            "<if test='e.act!=null'>" +
            " `activity_id`=#{e.act.id}  and " +
            "</if>" +
            "<if test='e.user!=null'>" +
            " `user_id`=#{e.user.id}  and" +
            "</if>" +
            "  `activity_id` is not null " +
            "</script>")
    Integer countByExample(@Param("e") Example example);

    default Integer count(@Param("e") Example example) {
        return countByExample(example);
    }

}
