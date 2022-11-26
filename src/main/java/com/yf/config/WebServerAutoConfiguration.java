
package com.yf.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
 * @Author yangfeng
 * @Description // Web 服务器参数配置，可以配置默认错误页面
 * @Date 14:42 2022/11/24
 **/
@Configuration
public class WebServerAutoConfiguration {
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		//如果请求出现不同情况的异常状态，则转发到不同的异常处理路径
		ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
		ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
		ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
		ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage errorPage415 = new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "/error/415");
		ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		factory.addErrorPages(errorPage400, errorPage401, errorPage403, errorPage404, errorPage415, errorPage500);
		return factory;
	}
}
