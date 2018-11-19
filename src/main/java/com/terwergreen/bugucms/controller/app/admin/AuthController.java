/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.terwergreen.bugucms.controller.app.admin;

import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.base.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Terwer
 * @Date 2018/6/25 15:07
 * @Version 1.0
 * @Description TODO
 **/
@Controller
@RequestMapping("/auth")
public class AuthController extends BGBaseController {

    @Autowired
    private CommonService commonService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        model.addAttribute("siteConfigDTO", siteConfigDTO);
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        model.addAttribute("siteConfigDTO", siteConfigDTO);
        return "auth/register";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgot(Model model) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        model.addAttribute("siteConfigDTO", siteConfigDTO);
        return "auth/forgot";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
