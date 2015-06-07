package edu.ccu.comp.se.commons.web.jcaptcha;

import java.awt.image.BufferedImage;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

import edu.ccu.comp.se.commons.ImageCodeConstants;

public class JCImageCaptchaService extends
		AbstractManageableImageCaptchaService implements ImageCaptchaService {

	private static JCImageCaptchaService instance;

	private static ListImageCaptchaEngine engine;

	public static JCImageCaptchaService getInstance() {
		if (instance == null) {
			engine = new JCListImageCaptchaEngine();
			instance = new JCImageCaptchaService(
					new FastHashMapCaptchaStore(), engine, 
					ImageCodeConstants.IMAGE_CODE_minGuarantedStorageDelayInSeconds, 
					ImageCodeConstants.IMAGE_CODE_maxCaptchaStoreSize, 
					ImageCodeConstants.IMAGE_CODE_captchaStoreLoadBeforeGarbageCollection);
		}
		return instance;
	}

	private JCImageCaptchaService(CaptchaStore captchaStore,
			CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds,
			int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
		super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds,
				maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
	}

	@Override
	public BufferedImage getImageChallengeForID(String ID)
			throws CaptchaServiceException {
		BufferedImage image=  super.getImageChallengeForID(ID);
		String generatedWord = ((JCListImageCaptchaEngine) engine).getWordBridge()
		.getGeneratedWord();
		WordMap.getWordsMap().put(ID, generatedWord);
		return image;
	}

}
