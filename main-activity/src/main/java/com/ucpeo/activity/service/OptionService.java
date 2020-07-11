package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.Option;
import com.ucpeo.activity.dao.OptionDao;
import java.util.List;

public  interface OptionService  {
    void create(Option option);

    void delete(Integer id);

    void update(Option option);

    Option get(Integer id);

    List<Option> getAll();

    List<Option> getFormOptions(Integer formId);

    List<Option> getInputOptions(Integer partFromId);

    void saveOptionInput(Integer partFormId, List<Option> optionList);

    void deleteOptionInput(Integer optionId, Integer partakeFormId);


    void batchSaveOption(Integer actFormId, List<Option> optionList);
}

