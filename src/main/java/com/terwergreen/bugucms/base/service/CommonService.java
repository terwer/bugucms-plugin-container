package com.terwergreen.bugucms.base.service;

import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.base.exception.BusinessServiceException;

/**
 * 公共服务
 */
public interface CommonService {
    /**
     * 获取站点配置
     *
     * @return
     * @throws BusinessServiceException
     */
    SiteConfigDTO getSiteConfig() throws BusinessServiceException;

    /**
     * 更新单个站点配置项
     *
     * @param optionName
     * @return
     * @throws BusinessServiceException
     */
    Integer updateSiteConfig(String optionName, String optionValue) throws BusinessServiceException;

    /**
     * 获取属性
     *
     * @param optionGroup
     * @return
     * @throws BusinessServiceException
     */
    Object getOption(String optionGroup) throws BusinessServiceException;

    /**
     * 更新属性
     *
     * @param optionName
     * @param optionGroup
     * @return
     * @throws BusinessServiceException
     */
    Integer updateOption(String optionName, String optionValue, String optionGroup) throws BusinessServiceException;
}
