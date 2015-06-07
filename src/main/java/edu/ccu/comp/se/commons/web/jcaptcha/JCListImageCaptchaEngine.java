package edu.ccu.comp.se.commons.web.jcaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptchaFactory;

import edu.ccu.comp.se.commons.ImageCodeConstants;


/**
 * from https://code.google.com/p/musicvalley/source/browse/trunk/musicvalley/doc/springSecurity/springSecurityIII/src/main/java/com/spring/security/jcaptcha/GMailEngine.java?spec=svn447&r=447
 * JCaptcha验证码图片生成引擎, 仿照JCaptcha2.0编写类似GMail验证码的样式.
 */

public class JCListImageCaptchaEngine extends ListImageCaptchaEngine {
	private WordBridge wordBridge;
	

	public JCListImageCaptchaEngine() {
		super();
	}


	protected void buildInitialFactories() {
		TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(ImageCodeConstants.IMAGE_CODE_MIN_ACCEPTED_WORD_LENGTH),
				new Integer(ImageCodeConstants.IMAGE_CODE_MAX_ACCEPTED_WORD_LENGTH), new RandomListColorGenerator(new Color[]{
		                new Color(23, 170, 27), new Color(220, 34, 11),
		                new Color(23, 67, 172)}), new TextDecorator[]{});
		
        BackgroundGenerator background = new UniColorBackgroundGenerator(
        		ImageCodeConstants.IMAGE_CODE_BACKGROUND_WIDTH, ImageCodeConstants.IMAGE_CODE_BACKGROUND_HEIGHT, Color.white);
        FontGenerator font = new RandomFontGenerator(ImageCodeConstants.IMAGE_CODE_FONT_SIZE, ImageCodeConstants.IMAGE_CODE_FONT_SIZE,
                new Font[]{new Font("nyala", Font.BOLD, ImageCodeConstants.IMAGE_CODE_FONT_SIZE),
                        new Font("Bell MT", Font.PLAIN, ImageCodeConstants.IMAGE_CODE_FONT_SIZE),
                        new Font("Credit valley", Font.BOLD, ImageCodeConstants.IMAGE_CODE_FONT_SIZE)});

        ImageDeformation postDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation backDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation textDef = new ImageDeformationByFilters(
                new ImageFilter[]{});

        WordToImage word2image = new DeformedComposedWordToImage(font,
                background, randomPaster, backDef, textDef, postDef);

     
        ImageCaptchaFactory factory = new JCGimpyFactory(
				new RandomWordGenerator(ImageCodeConstants.IMAGE_CODE_ACCEPTED_CHARS),
				word2image);
        
		wordBridge = ((JCGimpyFactory)factory).getWordBridge();
		
		ImageCaptchaFactory characterFactory[] = { factory};
		this.addFactories(characterFactory);

	}


	public WordBridge getWordBridge() {
		return wordBridge;
	}
	
	
	
	
	
	

}
