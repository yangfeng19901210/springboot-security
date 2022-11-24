package com.yf.config.service.impl;

import com.yf.config.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @projectName: springboot-security
 * @package: com.yf.config.service.impl
 * @className: TestServiceImpl
 * @author: yangfeng
 * @description: TODO
 * @date: 2022/11/23 19:12
 * @version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void getOrd(String ordNo) {
        System.out.println(ordNo);
    }

    @Override
    public void getOrd(String ordNo, Object obj) {
        this.getOrd(ordNo);
        if(null!=obj){
            System.out.println("执行数据的更新操作");
        }
    }
}
