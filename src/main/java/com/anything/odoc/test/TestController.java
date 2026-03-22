package com.anything.odoc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/test")
    public TestVO postTest(@RequestBody TestVO testvo) {
        testvo.setTestNm(testService.selectName());
        return testvo;
    }

    @GetMapping("/test")
    public TestVO getTest(TestVO testvo) {
        testvo.setTestNm("getTestNm");
        return testvo;
    }
}
