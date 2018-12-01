package com.terwergreen.bugucms.common;

import com.terwergreen.core.CommonDAO;
import com.terwergreen.core.CommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/11/26 15:16
 * @Version 1.0
 * @Description 公共服务
 **/
@Service
public class CommonServiceImpl implements CommonService {
    private static final Log logger = LogFactory.getLog(CommonServiceImpl.class);

    private static final String SITE_CONFIG_KEY = "siteConfig";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public Object getSiteConfig(String optionName) {
        String result = null;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionGroup", SITE_CONFIG_KEY);
            paramMap.put("optionName", optionName);
            Map resultMap = (Map) commonDAO.querySingleByMap("getOptionByGroup", paramMap);
            if (null != resultMap) {
                result = (String) resultMap.get("optionValue");
            }
        } catch (Exception e) {
            logger.error("获取配置项异常", e);
        }
        return result;
    }

    @Override
    public Object getOption(String optionGroup) {
        List list = null;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionGroup", optionGroup);
            list = commonDAO.queryListByMap("getOptionByGroup", paramMap);
        } catch (Exception e) {
            logger.error("获取配置项异常", e);
        }
        //多个结果返回List，只有一个结果的时候直接返回
        if (!CollectionUtils.isEmpty(list)) {
            if (list.size() > 1) {
                return list;
            } else {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Integer updateSiteConfig(String optionName, String optionValue) {
        return updateOption(optionName, optionValue, SITE_CONFIG_KEY);
    }

    @Override
    public Integer updateOption(String optionName, String optionValue, String optionGroup) {
        Integer result = 0;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionName", optionName);
            paramMap.put("optionValue", optionValue);
            paramMap.put("optionGroup", optionGroup);
            result = commonDAO.update("updateOptionByGroup", paramMap);
        } catch (Exception e) {
            logger.error("获取站点配置异常", e);
        }
        return result;
    }
}
