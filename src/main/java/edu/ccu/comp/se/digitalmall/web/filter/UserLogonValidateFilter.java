package edu.ccu.comp.se.digitalmall.web.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.codec.binary.Base64;


import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
import edu.ccu.comp.se.digitalmall.model.User;

/**
 * Servlet Filter implementation class UserLogonValidateFilter
 */
public class UserLogonValidateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserLogonValidateFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		User user = (User) req.getSession().getAttribute(LoginMessage.USER);
		if(user == null){
			String url = WebUtil.getRequestURLWithParam(req);//得到当前请求路径
			//String redirectUrl = new String(Base64.encodeBase64(url.getBytes()));
			String redirectUrl = URLEncoder.encode(url, "UTF-8");
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect("/user/login.html?redirectURL="+ redirectUrl);
		}else{
			chain.doFilter(request, response);
		}

		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
