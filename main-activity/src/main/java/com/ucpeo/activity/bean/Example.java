package com.ucpeo.activity.bean;


import org.apache.commons.beanutils.BeanMap;

import java.util.HashMap;

public class Example<T> extends HashMap {
    public Example(T exam) {
        super();

        this.putAll(new BeanMap(exam));

    }

    public Example() {
        super();
    }

    public static Example parse(Object exam) {
        Example example = new Example();
        example.putAll(new BeanMap(exam));
        return example;
    }
}

