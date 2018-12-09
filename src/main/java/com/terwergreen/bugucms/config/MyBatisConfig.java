package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis扫描路径配置
 */
@ConditionalOnProperty(name = "bugucms.plugin-switch", havingValue = "true")
@Configuration
public class MyBatisConfig implements TransactionManagementConfigurer {
    private static final Log logger = LogFactory.getLog(MyBatisConfig.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BugucmsPluginManager pluginManager;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        // 分页插件(这里配置无效，需要在配置文件配置)
        // PageInterceptor pageInterceptor = new PageInterceptor();
        // Properties properties = new Properties();
        // properties.setProperty("reasonable", "true");
        // properties.setProperty("supportMethodsArguments", "true");
        // properties.setProperty("returnPageInfo", "check");
        // properties.setProperty("params", "count=countSql");
        // properties.setProperty("helperDialect", "mysql");
        // pageInterceptor.setProperties(properties);
        // 添加插件
        // sqlSessionFactory.setPlugins(new Interceptor[]{pageInterceptor});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            logger.info("设置MyBatis映射路径");
            // 获取容器mappers映射文件
            Resource[] resources = resolver.getResources("classpath:mappers/*.xml");
            // sqlSessionFactory.setTypeAliasesPackage("com.terwergreen.pojo");

            // 获取插件管理区
            if (null == pluginManager) {
                pluginManager = (BugucmsPluginManager) applicationContext.getBean("pluginManager");
            }
            // 插件mappers映射文件
            List<PluginWrapper> plugins = pluginManager.getStartedPlugins();
            ArrayList<Class> clazzes = new ArrayList<>();
            for (PluginWrapper pluginWrapper : plugins) {
                // mappers文件目录
                String pluginMappersPath = "mappers";
                // 虚拟路径
                String prefix = pluginWrapper.getPluginId().replace("-plugin", "");
                // 实际路径
                String absPath = pluginWrapper.getPluginClassLoader().getResource(".").getPath();
                String pluginMappersLocation = absPath + pluginMappersPath;
                Path path = Paths.get(pluginMappersLocation);
                logger.info("plugins mappers location:" + pluginMappersLocation);
                if (Files.exists(path)) {
                    // 添加插件mappers文件映射
                    String filePath = "file://" + pluginMappersLocation + "/*.xml";
                    logger.info("添加" + pluginWrapper.getPluginId() + "插件mappers文件映射:" + filePath);
                    Resource[] pluginResources = resolver.getResources(filePath);
                    if (pluginResources.length > 0) {
                        logger.info("找到mappers映射文件,合并");
                        resources = ArrayUtils.addAll(resources, pluginResources);
                    }
                }

//                // 映射classType
//                String pojoPath = absPath + "com/terwergreen/plugins/" + prefix + "/pojo";
//                File file = new File(pojoPath);
//                File[] tempList = file.listFiles();
//                if (null != tempList && tempList.length > 0) {
//                    for (File f : tempList) {
//                        if (f.getName().endsWith(".class")) {
//                            String fullName = "com.terwergreen.plugins." + prefix + ".pojo." + f.getName().replace(".class", "");
//                            Class clazz = Class.forName(fullName, true, pluginWrapper.getPluginClassLoader());
//                            if (null != clazz) {
//                                logger.info("映射类型：" + clazz);
//                                clazzes.add(clazz);
//                            }
//                        }
//                    }
//                }
            }
            logger.info("合并完成：" + resources);
            sqlSessionFactory.setMapperLocations(resources);

//            Class[] typeAliases = new Class[clazzes.size()];
//            typeAliases = clazzes.toArray(typeAliases);
//            sqlSessionFactory.setTypeAliases(typeAliases);
//            // sqlSessionFactory.setTypeAliasesPackage("com.terwergreen.pojo");
//            // sqlSessionFactory.setTypeAliasesPackage("com.terwergreen.plugins.blog.pojo");

            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
