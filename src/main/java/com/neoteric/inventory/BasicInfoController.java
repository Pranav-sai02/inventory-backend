package com.neoteric.inventory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basic-info")
@CrossOrigin(origins = "http://localhost:3000")
public class BasicInfoController {

    @Autowired
    private BasicInfoService service;

    // POST /api/basic-info/create
    @PostMapping("/create")
    public BasicInfo create(@RequestBody BasicInfo basicInfo) {
        return service.save(basicInfo);
    }

    // GET /api/basic-info/all
    @GetMapping("/all")
    public List<BasicInfo> getAll() {
        return service.getAll();
    }

    // GET /api/basic-info/get/{id}
    @GetMapping("/get/{id}")
    public BasicInfo getById(@PathVariable Long id) {
        return service.getById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // PUT /api/basic-info/update/{id}
    @PutMapping("/update/{id}")
    public BasicInfo update(@PathVariable Long id, @RequestBody BasicInfo updatedInfo) {
        return service.update(id, updatedInfo);
    }

    // DELETE /api/basic-info/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

