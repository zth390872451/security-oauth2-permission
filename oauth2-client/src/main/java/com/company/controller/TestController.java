package com.company.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @PreAuthorize("hasRole('TEST')")
    @RequestMapping("/test2")
    public String test2() {
        return "success";
    }
}
