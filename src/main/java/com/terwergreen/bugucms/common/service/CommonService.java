package com.terwergreen.bugucms.common.service;

import com.terwergreen.bugucms.common.dto.SiteConfigDTO;
import com.terwergreen.core.service.BusinessServiceException;

/**
 * 公共服务
 */
public interface CommonService {
    SiteConfigDTO getSiteConfig() throws BusinessServiceException;
    Object getOption(String optionGroup) throws BusinessServiceException;
}
