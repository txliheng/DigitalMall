package edu.ccu.comp.se.commons.pagination;

/**
 * 分页标签提供者
  *
 */
public interface INavigationProvider {
    
    public String build(Page<?> page, String url);
    
}
