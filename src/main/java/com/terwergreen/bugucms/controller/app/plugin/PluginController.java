package com.terwergreen.bugucms.controller.app.plugin;

import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.utils.MathUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/plugin")
public class PluginController extends BGBaseController {
    @RequestMapping(value = "/hanglieshi")
    public String hanglieshi(Model model, String type, String input) throws Exception {
        if (!StringUtils.isEmpty(input)) {
            String[] arrays = input.split(" ");
            int result = MathUtils.Hanglieshi(arrays);
            model.addAttribute("input", input);
            model.addAttribute("output", result);
        }
        return "plugins/math/hanglieshi";
    }
}
