package com.terwergreen.bugucms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(ServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("服务器初始化，项目类型为：" + application.application().getWebApplicationType());
        return application.sources(BugucmsApplication.class);
    }

}
