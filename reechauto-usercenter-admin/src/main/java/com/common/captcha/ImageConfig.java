package com.common.captcha;


import java.awt.Color;

/**
 * http://www.weixueyuan.net/view/6074.html

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码图片生成器
 * 
 * @author
 */
public class ImageConfig {
	// 顺序执行的方法
	public String actions = "code,oval,#line,#grid,sin, twist,oval,#freeline,";

	// 图片的宽度，高度
	int width = 160;
	int height = 40;

	// 背景
	Color bgColor = ImageUtils.WHITE;
	boolean bgRandomColor = false;
	boolean bgRandomColorEach= false;
	boolean fromTo = false; //背景是否渐进
	Color bgColorFrom = ImageUtils.randomColor();
	Color bgColorTo = ImageUtils.weakColor(bgColorFrom, 0.5);
	
	//code
	int codeCount = 4;
	int codeMinSize = 22;// 最小字体 24
	int codeMaxSize = 42;// 字体是否随机 28
	int codeMaxOffset = height/3; // 字体垂直偏移量
	int codeMaxRadian = 30;// 字符倾斜最大角度
	int codeShadow = 0; // 阴影宽度
	Color codeColor = ImageUtils.GRAY;
	String codeStyle = "default";//默认字体
	boolean codeRandomColor = false;// 整体字体颜色是否随机
	boolean codeRandomColorEach = false;// 每个字体颜色是否随机
	boolean codeRandomSizeEach = false;// 字体大小是否随机
	boolean codeRandomStyleEach = false;// 字体是否随机

	// freeline 自由线
	int freeLineContinu = 1000;	//每方向上最多连续划线次数，越大曲线越平滑
	float freeLineWidth = 1.8f;	//默认粗细
	float freeLineMinWidth = 1.8f;	//随机粗细范围
	float freeLineMaxWidth =  3.8f;	//随机粗细范围
	boolean freeLineRandomWidth = true;	//是否随机粗细
	boolean freeLineRandomWidthEach = true; //是否随机粗细
	boolean freeLineColorSameWithCode = false;	//优先
	boolean freeLineRandomColor = true;		//颜色是否随机
	boolean freeLineRandomColorEach = false;	//每根颜色都不一样
	Color freeLineColor = codeColor;	//颜色
	
	// 扭曲度
	double twist = 2;

	// 画圈
	int ovalCount = 1;
	Color ovalColor = codeColor;
	boolean ovalColorSameWithCode = true;	//优先
	boolean ovalRandomColor = false;
	boolean ovalRandomColorEach = true;

	// 网格
	int gridXspan = 10;	//网格线X方向间距
	int gridYspan = 4;	//网格线Y方向间距
	int gridMaxRadian = 90;  //网格最大倾斜角度
	Color gridColor = bgColor;	//网格线颜色
	boolean gridRandomColor = false;	//网格线颜色是否随机
	boolean gridRandomColorEach = true;//每根网格线颜色都不一样

	// sin
	Color sinColor = codeColor; //Utils.weakColor(codeColor, 0.3);
	int sinMaxThick = 5;	//粗细
	boolean sinRandomColor = true;	
	boolean sinRandomColorEach = false;	
	public int sinPeriodPoint=200; //x方向一个周期占多少像素
	
	// 干扰线数
	int lineCount = 0;
	Color lineColor = Color.WHITE;
	boolean lineRandomColor = false;//线颜色是否随机
	boolean lineRandomColorEach = false;//每根线颜色都不一样
}
