package com.neoteric.inventory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {

    @Autowired
    private BasicInfoRepository repository;

    @Override
    public BasicInfo save(BasicInfo basicInfo) {
        return repository.save(basicInfo);
    }

    @Override
    public List<BasicInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BasicInfo> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BasicInfo update(Long id, BasicInfo updatedInfo) {
        return repository.findById(id).map(existing -> {
            updatedInfo.setId(id);
            return repository.save(updatedInfo);
        }).orElseThrow(() -> new RuntimeException("BasicInfo not found"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
