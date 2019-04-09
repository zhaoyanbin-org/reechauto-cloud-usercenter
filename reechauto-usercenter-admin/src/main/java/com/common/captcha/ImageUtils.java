package com.common.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class ImageUtils {
	static final Color RED = Color.RED;
	static final Color GREEN = Color.GREEN;
	static final Color BLUE = Color.BLUE;
	static final Color WHITE = Color.WHITE;
	static final Color GRAY = Color.GRAY;
	static final Color BLACK = Color.BLACK; 
	
	static Random random = new Random();
	
	// 去掉了容易误识别字符:0O1lijvy
    private static char[] codeSequence = "123456789ABCDEFGHJKLMNPRSTUVXYZ".toCharArray();
    
	static String[] fonts = { "Georgia", "Verdana", "Arial", "Tahoma",
			"Time News Roman", "Courier New", "Arial Black", "Quantzite" };
	
	static Font[] sysFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
 	static int[] styles = {Font.PLAIN, Font.BOLD,Font.ITALIC,Font.PLAIN, Font.ITALIC,Font.PLAIN};
	
	
	public static Font getFont(boolean isRandomFont,String fontName, int minSize, int maxSize) {
		
		int style = styles[random.nextInt(styles.length - 1)];
		int size = maxSize;
		if(maxSize != minSize){
			size = minSize + random.nextInt(maxSize - minSize);
		}
		if(isRandomFont){
			fontName = fonts[random.nextInt(fonts.length - 1)]; 
		}

		Font temp = null;
		try {
			if("default".equalsIgnoreCase(fontName)){
				temp = loadDefaultFont();
			}else{
				temp = new Font(fontName, style, size);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(temp == null){
			 return new Font(null, Font.PLAIN, size);
		}

		return temp.deriveFont(style, size);
		
	}

	public static Color randomColor() {
		return randomColor(0, 255);
	}
	
	/**
	 * 利用随机数,随机生成一个Color颜色的对象
	 * 
	 * @return颜色对象
	 */
	public static Color randomColor(int fc, int bc) {
		if (fc < 0)
			fc = 0;
		if (bc < 0)
			bc = 1;
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		if (bc == fc)
			bc += 10;
		int temp = 0;
		if (bc < fc) {
			temp = bc;
			bc = fc;
			fc = temp;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	/**
	 * 利用随机数,随机生成一个Color颜色的对象
	 * 
	 * @return颜色对象
	 */
	public static Color reverseColor(Color c) {
		return new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
	}
	
	/**
	 * 弱化颜色
	 * @param c
	 * @param rate: 0<rate<1, 颜色强度减少比例，0不减少
	 * @return
	 */
	public static Color weakColor(Color c, double rate) {
		rate=  1 + rate;
		if(rate < 0){
			rate = - rate;
		}
				
		double red = c.getRed() * rate;
		double green = c.getGreen() * rate;
		double blue = c.getBlue() * rate;
		  
		if(red > 255){
			red = 255;
		}
		if(green > 255){
			green = 255;
		}
		if(blue > 255){
			blue = 255;
		}
		return new Color((int)red, (int)green, (int)blue);
	}

	public static Color weakColor(Color source, Color to, double rate) {
		rate=  1 + rate;
		if(rate < 0){
			rate = - rate;
		}
				
		double red = source.getRed() + (source.getRed() - to.getRed())*rate;
		double green = source.getGreen() + (source.getGreen() - to.getGreen())*rate;
		double blue = source.getBlue() + (source.getBlue() - to.getBlue())*rate;
		
		if(red<0)red=-red;
		if(green<0)green=-green;
		if(blue<0)blue=-blue;
		
		if(red > 255){
			red = 255;
		}
		
		if(green > 255){
			green = 255;
		}
		if(blue > 255){
			blue = 255;
		}
		return new Color((int)red, (int)green, (int)blue);
	}

	public static char[] getRandomChars(int length) {
		char[] codes = new char[length];
		for (int i = 0; i < length; i++) {
			codes[i] = codeSequence[random.nextInt(codeSequence.length)];
		}
		return codes;
	}
	
    private static Font defaultFont = null;
    // 加载字体文件
    static Font loadDefaultFont() {
        if (defaultFont != null) {
            return defaultFont;
        }

        try {
            URL url = ImageUtils.class.getResource("arial.ttf");
            if (url == null) {
                return null;
            }
            InputStream input = url.openStream();
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return defaultFont;
    }
}
