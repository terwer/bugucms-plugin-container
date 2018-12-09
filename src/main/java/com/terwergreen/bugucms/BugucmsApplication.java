package com.terwergreen.bugucms;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.plugins.PluginInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BugucmsApplication {
    private static final Log logger = LogFactory.getLog(BugucmsApplication.class);

    public static void main(String[] args) {
        // 获取项目的配置类型
        // WebApplicationType applicationType = PropertyUtil.readProperty("bugucms.web.application-type").equals("servlet") ? WebApplicationType.SERVLET : WebApplicationType.REACTIVE;
        //设置应用类型
        SpringApplication springApplication = new SpringApplication(BugucmsApplication.class);
        // springApplication.setWebApplicationType(applicationType);
        springApplication.run(args);
    }

    @Bean
    public ApplicationRunner run() {
        return new ApplicationRunner() {
            @Value("${bugucms.plugin-switch}")
            private boolean pluginSwitch;

            @Value("${bugucms.web.application-type}")
            private String applicationType;

            @Autowired(required = false)
            private BugucmsPluginManager bugucmsPluginManager;

            @Override
            public void run(ApplicationArguments args) {
                // 输出插件信息
                if (pluginSwitch) {
                    List<PluginInterface> plugins = bugucmsPluginManager.getExtensions(PluginInterface.class);
                    logger.info(String.format("共找到%d个插件", plugins.size()));
                    plugins.forEach(c -> logger.info("插件:" + c.getClass().getName() + ":" + c.identify()));
                }
                logger.info("容器启动完毕，项目类型为：" + applicationType.toUpperCase());
                logger.info("SUCCESS");
            }
        };
    }
}
