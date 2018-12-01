package com.terwergreen.bugucms;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.util.ReflectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BugucmsApplication {
    private static final Log logger = LogFactory.getLog(BugucmsApplication.class);

    public static void main(String[] args) {
        //设置应用类型
        SpringApplication springApplication = new SpringApplication(BugucmsApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }

    @Bean
    public ApplicationRunner run() {
        return new ApplicationRunner() {
            @Value("${bugucms.pluginSwitch}")
            private boolean pluginSwitch;

            @Autowired
            private BugucmsPluginManager bugucmsPluginManager;

            @Override
            public void run(ApplicationArguments args) {
                // 输出插件信息
                if (pluginSwitch) {
                    List plugins = bugucmsPluginManager.getExtensions(Object.class);
                    logger.info(String.format("Number of plugins found: %d", plugins.size()));
                    plugins.forEach(c -> {
                        logger.info("插件:" + c.getClass().getName() + ":" + ReflectUtil.invoke(c,"identify"));
                    });
                    logger.info("插件已启动");
                }
            }
        };
    }
}
