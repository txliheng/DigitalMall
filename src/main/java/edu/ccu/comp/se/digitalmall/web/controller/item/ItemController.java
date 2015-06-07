package edu.ccu.comp.se.digitalmall.web.controller.item;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
import edu.ccu.comp.se.digitalmall.model.Item;
import edu.ccu.comp.se.digitalmall.service.IItemService;

@Controller
@RequestMapping(value="/item")
public class ItemController {

	@Autowired
	IItemService itemService;
	@RequestMapping(value="/item.html",method=RequestMethod.GET)
	public String itemView(
			ModelMap model,
			@RequestParam(value="itemId")String itemId,
			HttpServletRequest request){
   
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );
		Item item = itemService.find(new Long(itemId));
		if(item!=null){
			model.addAttribute("title", item.getTitle());
			model.addAttribute("ItemTitle", item.getTitle());
			model.addAttribute("Description", item.getDescrption());
			model.addAttribute("PromoPrice",new BigDecimal("410.00"));
			model.addAttribute("Price",item.getPrice());
			model.addAttribute("SkuInfo", item.getSkuInfoMap());
		}
		return "home/page/item/item";
		
	}

	/**
	 * 根据itemId返回itemSkus的json 格式：
	 * {"skuMap":
	 * {";20549:296172561;1627207:93407511;":{"stock":"997","price":"3960","skuId":"96955008246"},
	 *  ";20549:72380707;1627207:381956393;":{"stock":"944","price":"3960","skuId":"96955008253"},
	 *  ";20549:72380707;1627207:93407511;":{"stock":"954","price":"3960","skuId":"96955008248"}},
	 *  "item":{"picUrl":"http://img03.taobaocdn.com/bao/uploaded/i3/TB1MFWqHpXXXXcIXXXXXXXXXXXX_!!0-item_pic.jpg","stock":"17701","price":"3960"},
	 *  "prop":{"尺　　码":{"20549:59280855":["36"],"20549:296172561":["35"],"20549:418624880":["39"],"20549:103189693":["38"],"20549:72380707":["37"]},
	 *  		"颜色分类":{"1627207:381956393":["豆沙灰色"],"1627207:93407511":["米杏色"],"1627207:28341":["黑色"],"1627207:30226":["浅粉色"]}},
	 *  {"尺码":[{"3:8":"36"},{"3:9":"37"},{"3:6":"34"},{"3:7":"35"},{"3:10":"38"},{"3:11":"39"}],
	 *  "颜色":[{"2:3":"黑色"},{"2:4":"白色"},{"2:5":"粉色"}]}
	 *  "success":true}
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item_sku.do")
	@ResponseBody
	public String getSkuInfo(
	@RequestParam(value="itemId")String itemId){
		Item item = itemService.find(new Long(itemId));
		if(item!=null){
			return  JSON.toJSONString(item.getSkuInfoMap());
		}else{
			return "\"success\":"+ Boolean.FALSE;
		}
	}


}
