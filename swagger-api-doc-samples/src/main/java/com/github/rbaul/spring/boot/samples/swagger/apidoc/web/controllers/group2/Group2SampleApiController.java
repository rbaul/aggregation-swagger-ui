package com.github.rbaul.spring.boot.samples.swagger.apidoc.web.controllers.group2;

import com.github.rbaul.spring.boot.samples.swagger.apidoc.web.dtos.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("group2")
@RestController
public class Group2SampleApiController {

    @PostMapping("sample1")
    public void createSample1(@RequestBody SampleDto sampleDto){
        log.info("Sample created: {}", sampleDto);
    }

    @PostMapping("sample2")
    public void createSample2(@RequestBody SampleDto sampleDto){
        log.info("Sample created: {}", sampleDto);
    }

    @PostMapping("sample3")
    public void createSample3(@RequestBody SampleDto sampleDto){
        log.info("Sample created: {}", sampleDto);
    }

    @PostMapping("sample4")
    public void createSample4(@RequestBody SampleDto sampleDto){
        log.info("Sample created: {}", sampleDto);
    }

}
