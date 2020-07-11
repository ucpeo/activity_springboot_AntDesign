package com.ucpeo.activity.dao;

import com.ucpeo.activity.bean.Example;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface BaseDao<T> {
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void create(T t);

    void delete(Integer id);

    void update(T t);

    T get(Integer id);

    List<T> getAll();


    List<T> listByExample(Example<T> example);

    Integer count(Example<T> example);

}
