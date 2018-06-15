package com.terwergreen.bugucms.middle.common.service;

import com.terwergreen.core.service.BusinessServiceException;
import com.terwergreen.bugucms.middle.common.dto.SiteConfigDTO;

/**
 * 公共服务
 */
public interface CommonService {
    SiteConfigDTO getSiteConfig() throws BusinessServiceException;
    Object getOption(String optionGroup) throws BusinessServiceException;
}
