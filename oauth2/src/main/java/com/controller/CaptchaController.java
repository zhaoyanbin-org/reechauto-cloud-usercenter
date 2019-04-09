package com.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.Constants;
import com.common.captcha.CaptchaImageGenerator;
import com.common.captcha.ImageConfig;


/**
 * 验证码
 * 
 * @author zhutw
 *
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

	private static ImageConfig config = new ImageConfig();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		CaptchaImageGenerator code = new CaptchaImageGenerator(config);
		//code.createCode("1111".toCharArray()); //方便开发固定验证码
		code.createCode(4);
		String value = code.getCode();
		session.setAttribute(Constants.SESSION_CAPTCHA_CODE_KEY, value);
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setIntHeader("Expires", -1);
		ImageIO.write(code.getBuffImg(), "jpeg", response.getOutputStream());
	}
}
