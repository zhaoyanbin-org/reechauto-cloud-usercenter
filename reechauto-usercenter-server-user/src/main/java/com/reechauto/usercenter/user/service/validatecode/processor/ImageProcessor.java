package com.reechauto.usercenter.user.service.validatecode.processor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.reechauto.usercenter.user.bean.ReechAuthConstant;
import com.reechauto.usercenter.user.service.validatecode.bean.CodeBean;

@Service("imageProcessor")
public class ImageProcessor {

	public void send(CodeBean bean, HttpServletResponse resp) throws Exception {
		int width = ReechAuthConstant.VALIDATE_IMAGE_WIDTH;
		int height = ReechAuthConstant.VALIDATE_IMAGE_HEIGHT;
		BufferedImage image = createBufferedImage(bean.getCode(), width, height);
		resp.setContentType("image/jpeg");
		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		ImageIO.write(image, "JPEG", resp.getOutputStream());
	}

	private BufferedImage createBufferedImage(String code, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Courier", Font.ITALIC, 30));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		char[] randArr = code.toCharArray();
		int length = randArr.length;
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(randArr[i]);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 15 * i + 8, 22);
		}
		g.dispose();
		return image;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
