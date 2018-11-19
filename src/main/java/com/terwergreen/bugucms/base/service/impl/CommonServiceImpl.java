package com.terwergreen.bugucms.base.service.impl;

import com.terwergreen.bugucms.base.dao.CommonDAO;
import com.terwergreen.bugucms.base.exception.BusinessServiceException;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.utils.BeanUtils;
import com.terwergreen.bugucms.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    private final Log logger = LogFactory.getLog(getClass());

    @Resource
    private CommonDAO commonDAO;

    @Override
    public SiteConfigDTO getSiteConfig() throws BusinessServiceException {
        SiteConfigDTO siteConfigDTO = null;
        try {
            List<Map<String, Object>> list = (List<Map<String, Object>>) getOption(Constants.SITE_CONFIG_KEY);
            siteConfigDTO = BeanUtils.optionList2bean(list, SiteConfigDTO.class);
        } catch (Exception e) {
            logger.error("获取站点配置异常", e);
            throw new BusinessServiceException(e);
        }
        return siteConfigDTO;
    }

    @Override
    public Integer updateSiteConfig(String optionName, String optionValue) throws BusinessServiceException {
        return updateOption(optionName, optionValue, Constants.SITE_CONFIG_KEY);
    }

    @Override
    public Object getOption(String optionGroup) throws BusinessServiceException {
        List list = null;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionGroup", optionGroup);
            list = commonDAO.queryListByMap("get_option_by_group", paramMap);
        } catch (Exception e) {
            logger.error("获取配置项异常", e);
            throw new BusinessServiceException(e);
        }
        //多个结果返回List，只有一个结果的时候直接返回
        if (list.size() > 0) {
            return list;
        } else {
            return list.get(0);
        }
    }


    @Override
    public Integer updateOption(String optionName, String optionValue, String optionGroup) throws BusinessServiceException {
        Integer result = 0;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionName", optionName);
            paramMap.put("optionValue", optionValue);
            paramMap.put("optionGroup", optionGroup);
            result = commonDAO.update("update_option_by_group", paramMap);
        } catch (Exception e) {
            logger.error("获取站点配置异常", e);
            throw new BusinessServiceException(e);
        }
        return result;
    }

}
