package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserDao extends BaseDao<User> {
    @Override
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO `users` (\n" +
            "  `id`,\n" +
            "  `username`,\n" +
            "  `full_name`,\n" +
            "  `password`,\n" +
            "  `email`,\n" +
            "  `phone`,\n" +
            "  `state`,\n" +
            "  `birthday`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{username},\n" +
            "    #{fullName},\n" +
            "    #{password},\n" +
            "    #{email},\n" +
            "    #{phone},\n" +
            "    #{state},\n" +
            "    #{birthday}\n" +
            "  ) ;")
    void create(User user);

    @Override
    @Delete("delete from `users` where id = #{id}")
    void delete(@Param("id") Integer id);

    @Override
    @Update("update `users` set   " +
            "  `username` = #{username},\n" +
            "  `full_name` = #{fullName},\n" +
            "  `password` = #{password},\n" +
            "  `email` = #{email},\n" +
            "  `phone` = #{phone},\n" +
            "  `state` = #{state},\n" +
            "  `birthday` = #{birthday} \n" +
            "WHERE `id` = #{id} ;")
    void update(User user);

    @Override
    @Select("select * from `users` where id = #{id}")
    @Results(id = "user", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "full_name", property = "fullName"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "id", property = "actList", many = @Many(select = "com.ucpeo.activity.dao.ActDao.getActsByUser", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "partakeList", many = @Many(select = "com.ucpeo.activity.dao.PartakeDao.getPartsByUser", fetchType = FetchType.LAZY))
    })
    User get(@Param("id") Integer id);

    @Override
    @Select("select * from `users`")
    @ResultMap("user")
    List<User> getAll();

    @Select("select * from users where username=#{value} limit 0 ,1")
    @ResultMap("user")
    User getByUsername(String username);

    @Select("select * from users where username=#{username} and password=#{password} limit 0 ,1")
    @ResultMap("user")
    User login(@Param("username") String username,@Param("password") String password);

}
