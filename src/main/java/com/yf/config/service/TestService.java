package com.yf.config.service;

/**
 * @projectName: springboot-security
 * @package: com.yf.config.service
 * @className: TestService
 * @author: yangfeng
 * @description: TODO
 * @date: 2022/11/23 19:11
 * @version: 1.0
 */
public interface TestService {

    void getOrd(String ordNo);
    void getOrd(String ordNo,Object obj);
}
