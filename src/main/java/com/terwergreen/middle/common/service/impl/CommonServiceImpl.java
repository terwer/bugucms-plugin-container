package com.terwergreen.middle.common.service.impl;

import com.terwergreen.framework.core.bg.biz.service.BaseService;
import com.terwergreen.framework.core.bg.biz.service.BusinessServiceException;
import com.terwergreen.front.common.util.BeanUtils;
import com.terwergreen.middle.common.dao.CommonDAO;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
import com.terwergreen.middle.common.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl extends BaseService implements CommonService {

    @Autowired
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
    public Object getOption(String optionGroup) throws BusinessServiceException {
        List list = null;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionGroup", optionGroup);
            list = commonDAO.queryList("get_option_by_group", paramMap);
        } catch (Exception e) {
            logger.error("获取配置项异常", e);
        }
        //多个结果返回List，只有一个结果的时候直接返回
        if (list.size() > 0) {
            return list;
        } else {
            return list.get(0);
        }
    }
}
