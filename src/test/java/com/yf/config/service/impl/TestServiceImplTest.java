package com.yf.config.service.impl;

import com.yf.config.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TestServiceImplTest {

    @Resource
    private TestService testService;

    @Test
    @DisplayName("测试获取订单方法")
    void getOrd() {
        testService.getOrd("20130967");
    }

    @Test
    @DisplayName("测试获取订单方法并更新对应的数据")
    void testGetOrd() {
        testService.getOrd("036789210",3);
    }
}