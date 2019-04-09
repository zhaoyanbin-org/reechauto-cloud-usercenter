package com.common.captcha;

/**
 * http://www.weixueyuan.net/view/6074.html
 *
 */
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码图片生成器
 *
 * @author
 */
public class CaptchaImageGenerator {
	private static Random random = new Random();
	private ImageConfig config;

	private int height;
	private int width;
	private Color codeColor;
	private Color bgColor = null;
	private Color bgColorFrom;
	private Color bgColorTo;

	// ################################################
	// 验证码
	private char[] codes = null;
	private BufferedImage buffImg = null;
	private Graphics2D graphics = null;

	// ################################################
	public CaptchaImageGenerator(ImageConfig config) {
		this.config = config;
		this.height = config.height;
		this.width = config.width;
		this.bgColor = config.bgColor;
		this.codeColor = config.codeColor;
		this.bgColorFrom = config.bgColorFrom;
		this.bgColorTo = config.bgColorTo;
	}

	// ################################################
	public void createCode() {
		 createCode(config.codeCount);
	}
	public void createCode(int length) {
		createCode(ImageUtils.getRandomChars(length));
	}
	public void createCode(char[] code){
		codes = code;
		// 新建图像buffer
		buffImg = buildBufferImage(true);
		graphics = buffImg.createGraphics();

		// action
		String actions = config.actions.toLowerCase();
		String[] actionArray = actions.split(",");
		for (String action : actionArray) {
			action = action.trim();
			if ("sin".equals(action.trim())) {
				drawSin();
			} else if ("code".equalsIgnoreCase(action)) {
				drawCode();
			} else if ("oval".equalsIgnoreCase(action)) {
				drawOval();
			} else if ("grid".equalsIgnoreCase(action)) {
				drawGrid();
			} else if ("line".equalsIgnoreCase(action)) {
				drawLine();
			} else if ("twist".equalsIgnoreCase(action)) {
				twistImage();
			} else if ("freeline".equalsIgnoreCase(action)) {
				drawFreeLine();
			}
		}
	}

	// ################################################
	private BufferedImage buildBufferImage(boolean init) {
		BufferedImage tempImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D tempGtaphics = tempImage.createGraphics();

		// -----------------背景
		if(init){
			if(config.bgRandomColor){
				bgColor = ImageUtils.randomColor(100,250); //采用比较浅的颜色
				bgColorFrom = bgColor;
				bgColorTo = ImageUtils.weakColor(bgColorFrom, 0.5 - (float)random.nextInt(10)/10);
			}
		}

		// 碎点
		if(config.bgRandomColor && config.bgRandomColorEach){
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					tempImage.setRGB(i, j, ImageUtils.randomColor().getRGB());
				}
			}
			return tempImage;
		}

		if (config.fromTo) {
			// 渐变
			Paint paint = new GradientPaint(0, 0, bgColorFrom, width, height, bgColorTo);
			tempGtaphics.setPaint(paint);
			tempGtaphics.fillRect(0, 0, width, height);
		} else {
			tempGtaphics.setColor(bgColor);
			tempGtaphics.fillRect(0, 0, width, height);
		}

		return tempImage;
	}


	private void drawCode() {
		int cWidth = width / (codes.length + 1);// 每个字符的宽度
		int codeY = height - 2;
		int index = 0;

		// 字体颜色
		if (config.codeRandomColor) {
			//codeColor = ImageUtils.randomColor(10,200);//采用比较深的颜色
			codeColor = ImageUtils.reverseColor(bgColor);//背景反色
		}

		for (char code : codes) {
			if (config.codeRandomColor && config.codeRandomColorEach) {
				codeColor = ImageUtils.randomColor();
			}
			// 字体,高度,粗细
			if (config.codeRandomSizeEach) {
				graphics.setFont(ImageUtils.getFont(config.codeRandomStyleEach, config.codeStyle, config.codeMinSize, config.codeMaxSize));
			} else{
				graphics.setFont(ImageUtils.getFont(config.codeRandomStyleEach, config.codeStyle, config.codeMaxSize, config.codeMaxSize));
			}

			// 旋转度数radian
			if (config.codeMaxRadian > 0) {
				double radian = Math.toRadians(config.codeMaxRadian - random.nextInt(config.codeMaxRadian * 2));
				// Y坐标
				int tempy = codeY - random.nextInt(config.codeMaxOffset);
				graphics.rotate(radian, index * cWidth + cWidth, tempy);

				if (config.codeShadow > 0) {
					Color shadowColor = codeColor;
					for (int i = 1; i <= config.codeShadow; i++) {
						shadowColor = ImageUtils.weakColor(shadowColor, 0.1);
						//shadowColor = ImageUtils.weakColor(shadowColor, bgColor, 0.1);
						graphics.setColor(shadowColor);
						graphics.drawString(String.valueOf(code), index * cWidth + cWidth / 2 + i, tempy);
					}
				}
				graphics.setColor(codeColor);
				graphics.drawString(String.valueOf(code), index * cWidth + cWidth / 2, tempy);
				graphics.rotate(-radian, index * cWidth + cWidth, tempy);
			} else {
				graphics.drawString(String.valueOf(code), index * cWidth + cWidth / 2,
						codeY);
			}

			index++;
		}
	}


	private void drawLine() {
		int xs = 0, ys = 0, xe = 0, ye = 0;
		Color c = config.lineColor;
		if (config.lineRandomColor) {
			c = ImageUtils.randomColor();
		}
		graphics.setColor(c);

		for (int i = 0; i < config.lineCount; i++) {
			if (config.lineRandomColorEach) {
				graphics.setColor(ImageUtils.randomColor());
			}
			graphics.setStroke(new BasicStroke());
			xs = random.nextInt(width * 10) % width;
			ys = random.nextInt(height * 10) % height;
			xe = random.nextInt(width * 10);
			ye = random.nextInt(height * 10);
			graphics.drawLine(xs, ys, xe, ye);
		}
	}


	private void drawSin() {
		Color c = config.sinColor;
		if (config.sinRandomColor) {
			c = ImageUtils.randomColor();
		}
		graphics.setColor(c);
		//graphics.setColor(ImageUtils.weakColor(c, 0.1f - random.nextInt(40)/100f));

		double dy;
		int d = - random.nextInt(width*10);
		int begin = random.nextInt(width/4);
		int eng = width/2 + random.nextInt(width);
		for (int i = begin; i < eng; i=i+2) {
			for (int j = 0; j < height; j=j+2) {
				if (config.sinRandomColorEach) {
					c = ImageUtils.randomColor();
					graphics.setColor(c);
				}
				dy = (d + i) * width/config.sinPeriodPoint * 2.3;
				dy = height/2 + height * Math.sin(Math.toRadians(dy))/2;
				graphics.fillRect(i, (int) (dy), random.nextInt(config.sinMaxThick), random.nextInt(config.sinMaxThick));
			}
		}
	}

	// 画圈圈
	private void drawOval() {
		// 颜色
		if (config.ovalColorSameWithCode) {
			graphics.setColor(codeColor);
		} else if (config.ovalRandomColor) {
			graphics.setColor(ImageUtils.randomColor());
		} else {
			graphics.setColor(config.ovalColor);
		}

		for (int i = 0; i < config.ovalCount; i++) {
			if (config.ovalRandomColor && config.ovalRandomColorEach) {
				graphics.setColor(ImageUtils.randomColor());
			}
			int w = 5 + random.nextInt(10);
			int h = 5 + random.nextInt(10);
			int tx = random.nextInt(width - w);
			int ty = random.nextInt(height - h);
			graphics.drawOval(tx, ty, w, h);
		}
	}

	private void drawFreeLine() {
		//颜色
		if (config.freeLineColorSameWithCode) {
			graphics.setColor(codeColor);
		} else if (config.freeLineRandomColor) {
			graphics.setColor(ImageUtils.randomColor());
		} else {
			graphics.setColor(config.freeLineColor);
		}

		//线宽
		Stroke stroke = new BasicStroke(config.freeLineWidth);
		if(config.freeLineRandomWidth){
			stroke = new BasicStroke(randomFloat(config.freeLineMinWidth, config.freeLineMaxWidth));
		}
		graphics.setStroke(stroke);

		// 划线范围
		int xmin = width/3 - random.nextInt(width/3);
		int xmax = width/2 + random.nextInt(width/2);

		int ymin = random.nextInt(height/5);
		int ymax = 3*height/5 + random.nextInt(1*height/5);

		int x1 = xmin;
		int x2 = xmin;
		int y1 =  random.nextInt(ymax - ymin);
		int y2 = ymin;

		int direction = 1;    	//方向，只有-1,+1
		int continu  = config.freeLineContinu/2 + random.nextInt(config.freeLineContinu/2+1);	//多少次改变一下方向

		for (; x1 < xmax; ) {
			x2 = x1  + random.nextInt(18);
			y2 = random.nextInt(3);

			if(y1 + y2 > ymax){
				direction = -direction;
			}else if(y1 + y2 < ymin){
				direction = -direction;
			}
			y2 = y1 + y2*direction;

			x1 += 2 - random.nextInt(3);
			continu--;
			if(continu <= 0){
				continu = continu - 1;
				direction = - direction;
			}

			//粗细
			if(config.freeLineRandomWidth && config.freeLineRandomWidthEach){
				if(config.freeLineMaxWidth > config.freeLineMinWidth)
				stroke = new BasicStroke(randomFloat(config.freeLineMinWidth, config.freeLineMaxWidth));
				graphics.setStroke(stroke);
			}

			if(config.freeLineRandomColor && config.freeLineRandomColorEach){
				graphics.setColor(ImageUtils.randomColor());
			}
			graphics.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		};

	}
	float randomFloat(float min, float max){
		float f = max;
		if(max < min){
			max = min;
			min = f;
		}
		return min + (max - min) * random.nextInt(1001)/1000;
	}

	/**
	 *
	 * @param g
	 *            原始图像
	 * @param vx
	 *            x方向间隔
	 * @param vy
	 *            y方向间隔
	 * @param c
	 * @param randomColor
	 * @param maxRadian
	 *            倾斜最大角度
	 * @return
	 */
	private void drawGrid() {
		graphics.setColor(config.gridColor);
		int vx = config.gridXspan + 1;
		int vy = config.gridYspan + 1;
		double radian = 0;
		if (config.gridMaxRadian != 0) {
			radian = Math.toRadians(config.gridMaxRadian - random.nextInt(config.gridMaxRadian * 2));
		}
		graphics.rotate(radian, 0, 0);

		// 画竖线
		for (int i = -width * 2; i < width * 2; i += vx) {
			if (config.gridRandomColor) {
				graphics.setColor(ImageUtils.randomColor());
			}
			graphics.drawLine(i, -height, i, height*3);
		}

		// 画横线
		for (int i = -height*2; i < height * 2; i += vy) {
			if (config.gridRandomColor) {
				graphics.setColor(ImageUtils.randomColor());
			}
			graphics.drawLine(-width, i, width*3, i);
		}

		graphics.rotate(-radian, 0, 0);
	}

	/**
	 *
	 * @Description:正弦曲线Wave扭曲图片
	 * @since 1.0.0
	 * @Date:2012-3-1 下午12:49:47
	 * @return BufferedImage
	 */
	private void twistImage() {
		double dMultValue = random.nextInt(7) + 3;// 波形的幅度倍数，越大扭曲的程序越高，一般为3
		double dPhase = random.nextInt(6);// 波形的起始相位，取值区间（0-2＊PI）

		BufferedImage destBi = buildBufferImage(false);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int nOldX = getXPosition4Twist(dPhase, dMultValue, i, j);
				int nOldY = j;
				if (nOldX >= 0 && nOldX < width && nOldY >= 0 && nOldY < height) {
					destBi.setRGB(nOldX, nOldY, buffImg.getRGB(i, j));
				}
			}
		}

		buffImg = destBi;
		graphics = buffImg.createGraphics();
	}

	/**
	 *
	 * @Description:获取扭曲后的x轴位置
	 * @since 1.0.0
	 * @Date:2012-3-1 下午3:17:53
	 * @param dPhase
	 * @param dMultValue
	 * @param height
	 * @param xPosition
	 * @param yPosition
	 * @return int
	 */
	private int getXPosition4Twist(double dPhase, double dMultValue, int xPosition, int yPosition) {
		double dx = (double) (config.twist * yPosition) / height + dPhase;
		double dy = Math.sin(dx);
		return xPosition + (int) (dy * dMultValue);
	}

	// 写文件
	public void write(String path) throws IOException {
		OutputStream sos = new FileOutputStream(path);
		this.write(sos);
	}

	private void write(OutputStream o) throws IOException {
		ImageIO.write(buffImg, "png", o);
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public String getCode() {
		return new String(codes);
	}

	public static void main(String[] args) {
		String path = "C:\\t\\test";
		ImageConfig config = new ImageConfig();
		CaptchaImageGenerator generator = new CaptchaImageGenerator(config);

		for (int i = 0; i < 10; i++) {
			try {
				// bg
//				config.bgColor = ImageUtils.WHITE;
//				config.fromTo = false;
//				config.bgColorFrom = ImageUtils.randomColor();
//				config.bgColorTo = ImageUtils.reverseColor(config.bgColorFrom);
//				config.bgRandomColorEach = false;
//
//				// code
//				config.codeCount =4;
//				config.codeColor = ImageUtils.GRAY;
//				config.codeRandomColor = false;
//				config.codeRandomColorEach = false;
//				config.codeRandomStyle = true;
//				config.codeMinSize = 22;
//				config.codeMaxSize = 38;
//				config.codeMaxOffset = 5;
//				config.codeMaxRadian = 20;
//				config.codeShadow = 5;
//
//				// sin
//				config.sinColor = config.codeColor;
//				config.sinMaxThick = 10;
//				config.sinRandomColor = false;
//				config.sinRandomColorEach = false;
//
//				// line
//				config.lineCount = 3;
//				config.lineColor = config.codeColor;
//				config.lineRandomColor = false;
//
//				// grid
//				config.gridColor = config.bgColor;
//				config.gridRandomColor = false;
//				config.gridXspan = 10;
//				config.gridYspan = 3;
//				config.gridMaxRadian = 10;
//
//				// oval
//				config.ovalCount = 5;
//				config.ovalColor = config.codeColor;
//				config.ovalRandomColor = false;
//				config.ovalRandomColorEach = true;
//				//
//				config.twist = 2;

				// #####
				config.actions = "code,oval,#line,#grid,sin, twist,oval,#freeline,";
				generator.createCode(4);
				generator.write(path + i + ".png");
				System.out.println("code"+i+"="+generator.getCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
