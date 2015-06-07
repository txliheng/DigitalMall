package edu.ccu.comp.se.commons.pagination.provider;

import edu.ccu.comp.se.commons.pagination.AbstractNavigationProvider;
import edu.ccu.comp.se.commons.pagination.Page;

/**
 * 构建如“上一页 1 2 3 下一页 ”形式分页
 *
 */
public class NavigationProviderV2 extends AbstractNavigationProvider {
    
//    private int number = 5;
    
    @Override
    public String build(Page<?> page, String url) {
        StringBuilder sb = new StringBuilder();
//为了简化测试，我们把不需要的注释掉，需要时去掉注释
//        if (page.isHasPre()) {
//            String preUrl = append(url, "pn", page.getIndex() - 1);
//            sb.append("<a href=\"" + preUrl + "\">上一页</a>&nbsp;");
//        } 
//        //显示当前页码的前2页码和后两页码 
//        //若1 则 1 2 3 4 5, 若2 则 1 2 3 4 5, 若3 则1 2 3 4 5,
//        //若4 则 2 3 4 5 6 ,若10  则 8 9 10 11 12
//        int currIndex = page.getIndex();
//        int startIndex = (currIndex - 2 > 0)? currIndex - 2 : 1;  
//        int pageCount = page.getContext().getPageCount();
//        for(int i = 1; i <= number && startIndex <= pageCount && pageCount > 1; startIndex++, i++) {
//            if(startIndex == currIndex) {
//                sb.append(startIndex + "&nbsp;");
//                continue;
//            }
//            String pageUrl  = append(url, "pn", startIndex);
//            sb.append("<a href=\"" + pageUrl + "\">"+ startIndex +"</a>&nbsp;");
//        }
//        if (page.isHasNext()) {
//            String nextUrl  = append(url, "pn", page.getIndex() + 1);
//            sb.append("<a href=\"" + nextUrl + "\">下一页</a>");
//        }
        return sb.toString();
    }
}
