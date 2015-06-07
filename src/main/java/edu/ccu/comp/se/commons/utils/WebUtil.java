package edu.ccu.comp.se.commons.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	/**
     * 添加cookie
     * @param response
     * @param name cookie的名称
     * @param value cookie的值
     * @param maxAge cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie立即删除; 如果值为负数,cookie将随浏览器关闭而清除)
     */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {        
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge>0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        
    }
	 /**
     * 获取cookie的值
     * @param request
     * @param name cookie的名称
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
    	Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie.getValue();
        }else{
            return null;
        }
    }
    
    protected static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }
    /***
     * 获取URI的路径,如路径为http://www.digitalmall.com/action/post.htm?method=add, 得到的值为"/action/post.htm"
     * @param request
     * @return
     */
    public static String getRequestURL(HttpServletRequest request){     
        return request.getRequestURL().toString();
    }
    /**
     * 获取完整请求路径(含内容路径及请求参数)
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public static String getRequestURLWithParam(HttpServletRequest request){     
    	Map<String, String[]> params = request.getParameterMap();  
        String queryString ="";  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&"; 
                
            }  
        }
        if(!"".equals(queryString))
        	queryString = queryString.substring(0, queryString.length() - 1);
    	
        return getRequestURL(request) + ("".equals(queryString) ? "" : "?"+ queryString);
    }
}
