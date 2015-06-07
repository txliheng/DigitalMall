package edu.ccu.comp.se.commons;

import java.awt.Color;

public interface ImageCodeConstants {

	/** 图片验证码 最少4位 */
	public static final int IMAGE_CODE_MIN_ACCEPTED_WORD_LENGTH = 4;
	/** 图片验证码 最多4位 */
	public static final int IMAGE_CODE_MAX_ACCEPTED_WORD_LENGTH = 4;
	/** 图片验证码颜色 */
	public static final Color IMAGE_CODE_COLOR_OF_WORD = Color.BLACK;
	/** 每位图片验证码干扰孔数量 */
	public static final int IMAGE_CODE_NUMBER_OF_HOLES_PER_GLYPH = 1;
	/** 图片验证码干扰孔颜色 */
	public static final Color IAMGE_CODE_COLOR_OF_HOLE = Color.WHITE;
	/** 图片验证码字库 */
	public static final String IMAGE_CODE_ACCEPTED_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
	/** 图片验证码字库默认像素 */
	public static final int IMAGE_CODE_FONT_SIZE = 40;
	/** 图片验证码字库最小像素 */
	public static final int IMAGE_CODE_MIN_FONT_SIZE = 34;
	/** 图片验证码字库最大像素 */
	public static final int IMAGE_CODE_MAX_FONT_SIZE = 40;
	/**图片验证码背景宽度 */
	public static final int IMAGE_CODE_BACKGROUND_WIDTH = 200;
	/**图片验证码背景高度 */
	public static final int IMAGE_CODE_BACKGROUND_HEIGHT = 70;
	/**图片验证码生成字符*/
	public static final String IMAGE_CODE_GENERATED_WORD = "generatedWord";
	
	public static final int IMAGE_CODE_minGuarantedStorageDelayInSeconds = 180;
	public static final int IMAGE_CODE_maxCaptchaStoreSize = 100000;
	public static final int IMAGE_CODE_captchaStoreLoadBeforeGarbageCollection = 75000;
	
}
