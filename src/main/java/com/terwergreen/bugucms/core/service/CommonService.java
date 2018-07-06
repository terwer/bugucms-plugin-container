package com.terwergreen.bugucms.core.service;

import com.terwergreen.bugucms.base.service.BusinessServiceException;
import com.terwergreen.bugucms.dto.SiteConfigDTO;

/**
 * 公共服务
 */
public interface CommonService {
    SiteConfigDTO getSiteConfig() throws BusinessServiceException;
    Object getOption(String optionGroup) throws BusinessServiceException;
}
