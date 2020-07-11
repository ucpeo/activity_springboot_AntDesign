package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.Option;
import com.ucpeo.activity.dao.OptionDao;
import com.ucpeo.activity.service.OptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Resource
    OptionDao optionDao;

    @Override
    public void create(Option option) {
        optionDao.create(option);
    }

    @Override
    public void delete(Integer id) {
        optionDao.delete(id);
    }

    @Override
    public void update(Option option) {
        optionDao.update(option);
    }

    @Override
    public Option get(Integer id) {
        return optionDao.get(id);
    }

    @Override
    public List<Option> getAll() {
        return optionDao.getAll();
    }

    @Override
    public List<Option> getFormOptions(Integer formId) {
        return optionDao.getFormOptions(formId);
    }

    @Override
    public List<Option> getInputOptions(Integer partFromId) {
        return optionDao.getInputOptions(partFromId);
    }

    @Override
    public void saveOptionInput(Integer partFormId, List<Option> optionList) {
        optionDao.saveOptionInput(partFormId, optionList);
    }

    @Override
    public void deleteOptionInput(Integer optionId, Integer partakeFormId) {
        optionDao.deleteOptionInput(optionId, partakeFormId);
    }

    @Override
    public void batchSaveOption(Integer actFormId, List<Option> optionList) {
        optionDao.batchSaveOption(actFormId, optionList);
    }
}
