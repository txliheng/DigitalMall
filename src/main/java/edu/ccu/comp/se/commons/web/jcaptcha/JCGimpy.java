package edu.ccu.comp.se.commons.web.jcaptcha;

import java.awt.image.BufferedImage;

import com.octo.captcha.image.ImageCaptcha;

public class JCGimpy extends ImageCaptcha {

	private String response;

	private boolean caseSensitive = true;

	protected JCGimpy(String question, BufferedImage challenge,
			String response,boolean caseSensitive) {
		super(question, challenge);
		this.response = response;
		this.caseSensitive = caseSensitive;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Boolean validateResponse(Object response) {

		return (null != response && response instanceof String) ? validateResponse((String) response)
				: Boolean.FALSE;
	}

	/**
	 * Very simple validation routine that compares the given response to the
	 * internal string.
	 * 
	 * @return true if the given response equals the internal response, false
	 *         otherwise.
	 */
	private final Boolean validateResponse(final String response) {

		return caseSensitive ? response.equals(this.response) : response
				.equalsIgnoreCase(this.response);
	}
}
