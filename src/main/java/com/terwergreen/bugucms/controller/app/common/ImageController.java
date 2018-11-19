package com.terwergreen.bugucms.controller.app.common;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.utils.Constants;
import com.terwergreen.bugucms.utils.RestResponseStates;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/common/image")
@ImportResource(locations = {"classpath:kaptcha.xml"})
public class ImageController extends BGBaseController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取图片校验码接口
     *
     * @return
     * @author terwergreen
     * @date 2018-04-02 13:36:00
     */
    @RequestMapping(value = "/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(Constants.SESSION_VERIFY_CODE_KEY, createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            super.logger.error("接口异常:error=", e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        logger.debug("获取图片校验码成功。");
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 校验图片校验码结果接口
     *
     * @return
     * @author terwergreen
     * @date 2018-04-02 13:36:00
     */
    @RequestMapping("/verifyKaptcha")
    @ResponseBody
    public RestResponseDTO verifyKaptcha(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "captcha", required = true) String captcha) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            response.setContentType("application/json;charset=utf-8");
            logger.debug("验证开始。");
            String captchaId = (String) request.getSession().getAttribute(Constants.SESSION_VERIFY_CODE_KEY);
            logger.debug("Session vrifyCode " + captchaId + ",form vrifyCode " + captcha);
            Map<String, String> paramMap = new HashMap<String, String>();
            if (!captchaId.equalsIgnoreCase(captcha)) {
                paramMap.put("info", "错误的验证码");
                paramMap.put("status", "0");
            } else {
                paramMap.put("info", "验证成功");
                paramMap.put("status", "1");
            }
            restResponseDTO.setData(paramMap);
            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("验证完成。");
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            return restResponseDTO;
        }
        return restResponseDTO;
    }
}
