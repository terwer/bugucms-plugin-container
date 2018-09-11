package com.terwergreen.bugucms.core.service.impl;

import com.terwergreen.bugucms.base.service.BaseService;
import com.terwergreen.bugucms.base.service.BusinessServiceException;
import com.terwergreen.bugucms.core.dao.CommonDAO;
import com.terwergreen.bugucms.core.service.CommonService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.util.BeanUtils;
import com.terwergreen.bugucms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    
@SuppressWarnings({ "unchecked", "rawtypes" })
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
}
