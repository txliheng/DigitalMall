package edu.ccu.comp.se.commons.pagination;

public abstract class AbstractNavigationProvider implements INavigationProvider {
   
    protected String append(String url, String key, int value) {
        return append(url, key, String.valueOf(value));
    }
    
    /**
     * 为url 参加参数对
     * 
     * @param url
     * @param key
     * @param value
     * @return
     */
    private String append(String url, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }
        
        if (url.indexOf("?") == -1) {
            url = url + "?" + key + "=" + value;
        } else {
            url = url + "&amp;" + key + "=" + value;
        }
        
        return url;
    }
}
