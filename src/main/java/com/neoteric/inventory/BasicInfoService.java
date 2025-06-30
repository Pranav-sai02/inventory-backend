package com.neoteric.inventory;




import java.util.List;
import java.util.Optional;

public interface BasicInfoService {
    BasicInfo save(BasicInfo basicInfo);
    List<BasicInfo> getAll();
    Optional<BasicInfo> getById(Long id);
    BasicInfo update(Long id, BasicInfo updatedInfo);
    void delete(Long id);
}
