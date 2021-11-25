package me.junhua.springbootvalidated.controller;

import me.junhua.springbootvalidated.config.ValidationConfig;
import me.junhua.springbootvalidated.dto.DeptDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dept")
public class TestController {

    @PostMapping("/save")
    public void save(@Validated(ValidationConfig.Create.class) @RequestBody DeptDTO sysDept) {
    }

    @PostMapping("/update")
    public void update(@Validated(ValidationConfig.Update.class) @RequestBody DeptDTO sysDept) {
    }
}
