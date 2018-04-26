package com.terwergreen.middle.common.service;

import com.terwergreen.framework.biz.service.BusinessServiceException;
import com.terwergreen.middle.common.dto.SiteConfigDTO;

/**
 * 公共服务
 */
public interface CommonService {
    SiteConfigDTO getSiteConfig() throws BusinessServiceException;
    Object getOption(String optionGroup) throws BusinessServiceException;
}
