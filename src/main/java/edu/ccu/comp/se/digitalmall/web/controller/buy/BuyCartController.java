package edu.ccu.comp.se.digitalmall.web.controller.buy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
import edu.ccu.comp.se.digitalmall.service.IItemSkuService;

@Controller
@RequestMapping(value="/buy")
public class BuyCartController {

	@Autowired
	IItemSkuService itemSkuService;
	/**
	 * 往购物车里添加商品
	 * @param request
	 * @param skuId
	 * @param itemId
	 * @param qty
	 * @param price
	 * @return
	 */
	@RequestMapping(value="/add_cart_item.html")
	public String addCartItem(
			HttpServletRequest request,
			@RequestParam(value="skuId")String skuId,
			@RequestParam(value="itemId")String itemId,
			@RequestParam(value="quantity")String qty,
			@RequestParam(value="price")String price){


		return "redirect:/buy/add_cart_succeed.html";
	}
	/**
	 * 
	 * @param request
	 * @param currentItem:{"itemId":41410215780,"skuId":72778917548,"num":1,"price":6900}
	 * @param shopId:108817875
	 * @param sellerId:1983016489
	 * @param items:[{"itemId":44193006242,"skuId":86748042902,"num":2},
		{"itemId":41410215780,"skuId":72778917548,"num":1},    
		{"itemId":41410215780,"skuId":72778917550,"num":3}]    
	 * @param callback:jsonp21
	 * @return
	 */	
	@RequestMapping(value="/addCartView.html")
	public String addCartView(
			HttpServletRequest request,
			@RequestParam(value="currentItem")String currentItem,
			@RequestParam(value="shopId")String shopId,
			@RequestParam(value="sellerId")String sellerId,
			@RequestParam(value="items")String items,
			@RequestParam(value="callback")String callback){


		return "redirect:/buy/add_cart_succeed.html";
	}
	/**
	 * 往购物车里添加商品成功返回页面
	 * Form Data:
		itemId:41410215780
		sellerId:1983016489
		shopId:108817875
		sellerNick:
		shopName:
		itemPic:
		skuId:72778917548
		cartCount:4
		itemCount:1
		price:69.00
		sourceTime:
		title:
	 * @return
	 */
	@RequestMapping(value="/add_cart_succeed.html")
	public String addCartSucceed(ModelMap model,HttpServletRequest request){
		model.addAttribute("title", "加入购物车成功");
		model.addAttribute("nick",WebUtil.getCookieByName(request, "nick"));
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );
		return "home/page/buy/cart/add_cart_succeed";
	}
	/**
	 * 购物车为空返回的页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cart_empty.html")
	public String cartEmpty(
			ModelMap model,
			HttpServletRequest request){
		model.addAttribute("title", "数码城");
		model.addAttribute("titleAffix", "我的购物车");
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );
		return "home/page/buy/cart/cart_empty";
	}
	/**
	 * 查看购物车
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cart.html")
	public String cartView(
			ModelMap model,
			HttpServletRequest request){
		model.addAttribute("title", "数码城");
		model.addAttribute("titleAffix", "我的购物车");
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );
		return "home/page/buy/cart/cart";
	}

	/**
	 * 异步修改购物车里商品的sku属性，购买数量，删除商品等
	 * @param data: 
	 * [{"shopId":"s_1586850590","comboId":0,"shopActId":0,
	 * "cart":[{"quantity":1,"cartId":"116203171819","skuId":"72300878545","itemId":"42619139429"}],
	 * "operate":["116203171819"],
	 * "type":"deleteSome"},
	 * {"shopId":"s_1983016489","comboId":0,"shopActId":0,
	 * "cart":[{"quantity":1,"cartId":"116169865767","skuId":"86748042902","itemId":"44193006242"}],
	 * "operate":["116169865767"],"type":"deleteSome"}]
	 * @param type: deletesome/delete/undelete/check/update/updateItemSku
	 * @return {
	 * "globalData":
	 * {"assocs":
	 * [["s_112862194_0"]],
	 * "bundRela":
	 * {"list":
	 * [{"bundles":["s_112862194"],
	 * "id":112862194,
	 * "title":"高妹女鞋潮流馆"}]},
	 * "cartUrl":"//cart.taobao.com/cart.htm?from=bmini",
	 * "currentPageSize":1,
	 * "diffTairCount":0,
	 * "h":"g,geyc4mjygexdcmzwfy4di",
	 * "invalidSize":0,
	 * "isAllBItem":false,
	 * "isAllCItem":false,
	 * "isDouble11Pre":false,
	 * "isEndPage":false,
	 * "isNext":true,
	 * "login":false,
	 * "openNoAttenItem":false,
	 * "page":1,
	 * "promotionCount":0,
	 * "quickSettlement":false,
	 * "showGodDialogue":false,
	 * "showRedEnvelope":false,
	 * "stockTenseCount":0,
	 * "totalSize":-1,
	 * "tpId":0,
	 * "weakenCart":false},
	 * "list":[{"bundles":[{"id":"s_112862194_0",
	 * "orders":[
	 * {"amount":
	 * {"demand":0,
	 * "limit":9223372036854775807,
	 * "max":109,"multiple":1,"now":2,"supply":0},
	 * "attr":";op:3960;
	 * cityCode:220104;
	 * irefer:www.taobao.com;",
	 * "cartActiveInfo":{"cartBcParams":"buyerCondition~0~~cartCreateTime~1432115862000", "endTime":0,"isDefault":false,"type":0,"wantStatus":0},
	 * "cartId":"116013270445","checked":false,"createTime":1432115862000,
	 * "cumulativeSales":0,"gmtModifiedTime":1432207251000,"id":"116013270445",
	 * "isAttention":true,"isCod":false,
	 * "isDouble11":false,
	 * "isDouble11halfDiscount":false,
	 * "isSellerPayPostfee":false,
	 * "isValid":true,
	 * "itemIcon":{"CART_EBOOK":[],"CART_IDENTITY":[],"CART_XIAOBAO":[{"img":"//img.alicdn.com/tps/i1/T1EQA5FpVgXXceOP_X-16-16.jpg","link":"//xiaobao.taobao.com/contract/item_service.htm?scm=1010.100.100.1&contract_id=7d&item_id=44303153323","name":"7天无理由","title":"7天无理由"},{"desc":"如实描述","img":"//img.alicdn.com/tps/i3/T1bnR4XEBhXXcQVo..-14-16.png","link":"//www.taobao.com/go/act/315/xfzbz_rsms.php?ad_id=&am_id=130011830696bce9eda3&cm_id=&pm_id=","title":"消费者保障服务，卖家承诺商品如实描述"}],
	 * "CART_YULIU":[]},
	 * "itemId":"44303153323",
	 * "leafCategory":50012027,
	 * "pic":"//img.taobaocdn.com/bao/uploaded/i3/TB1MFWqHpXXXXcIXXXXXXXXXXXX_!!0-item_pic.jpg",
	 * "preference":false
		"price":{"actual":0,"descend":0,"extraCharges":0,"finalpay":0,"now":3960,"oriPromo":3960,"origin":3960,"prepay":0,"save":0,"shardFee":0,"sum":7920},
		"seller":"lijie926723",
		"sellerId":112862194
		"shopId":"57921238",
		"shopName":"高妹女鞋潮流馆","shopUrl":"//store.taobao.com/shop/view_shop.htm?user_number_id=112862194",
		"skuId":"96955008245",
		"skuStatus":2,
		"skus":{"颜色分类":"浅粉色","尺码":"39[偏大半码]"},"title":"2015新款春季女鞋中口英伦小皮鞋低跟单鞋女士休闲粗跟平底潮",
		"toBuy":"taobao",
		"toBuyInfo":{"attributes":{"irefer":"www.taobao.com"}},"url":"//item.taobao.com/item.htm?id=44303153323","weight":0}],"type":"shop","valid":true}],
		"gmtCompare":1432115862000,"hasPriceVolume":false,"host":"C","id":"s_112862194","isGoldSeller":false,"isValid":true,"seller":"lijie926723","sellerId":"112862194","shopId":57921238,"title":"高妹女鞋潮流馆","type":"shop","url":"//store.taobao.com/shop/view_shop.htm?user_number_id=112862194"}],
		"success":true}

	 */
	@RequestMapping(value="/AsyncUpdateCart.do")
	@ResponseBody
	public String asyncUpdateCart(String data,String type){
		return "";
	}
	/**
	 * 首页点击迷你购物车,返回jsonp
	 * @param callback=MiniCart.setData
	 * @param t=1432887138463
	 * @return MiniCart.setData(
		{
			"status" : true,
            "period": "3",
            "isLogin": "true",
            "promotionCount" : 0,
            "quailtyTenseCount" : 0,
			"item" : [
		 				{
							"cartId": "117367657117",
							"itemId": "18049865645", 
							"title": "B26 奢华进口水钻水晶方钻满天星手表女白色大表盘时装表正品包邮", 
							"picUrl":"//img.taobaocdn.com/bao/uploaded/i3/18918034524184091/T1WQifXthgXXXXXXXX_!!1-item_pic.gif_40x40.jpg",
							"originPrice":"190.00",
							"price":"90.00",
							"sku" : ["颜色分类:b26圆形黄金色"], 
							"prefence":false
	    				}
	    											,
						{ 
							"cartId": "116729300408", 
							"itemId": "44055305538", 
							"title": "夏季透气网面男鞋气垫运动鞋男士休闲鞋韩版青春潮流板鞋单网潮鞋", 
							"picUrl":"//img.taobaocdn.com/bao/uploaded/i1/1854798875/TB22U6vcFXXXXaEXpXXXXXXXXXX_!!1854798875.jpg_40x40.jpg",
							"originPrice":"388.00",
							"price":"98.00",
							"sku" : ["颜色分类:276桔色","尺码:40[新款抢购中！]"	] , 
							"prefence":false
	    				}
	    											,
						{ 
							"cartId": "116855977265", 
							"itemId": "44055305538", 
							"title": "夏季透气网面男鞋气垫运动鞋男士休闲鞋韩版青春潮流板鞋单网潮鞋", 
							"picUrl":"//img.taobaocdn.com/bao/uploaded/i2/1854798875/TB2afPucFXXXXbhXpXXXXXXXXXX_!!1854798875.jpg_40x40.jpg",
							"originPrice":"388.00",
							  "price":"98.00",
							"sku" : ["颜色分类:997灰色"	,"尺码:40[新款抢购中！]"], 
							"prefence":false
	    				}
					],
			"num" : 3
					
		}
	)
	 */
	@RequestMapping(value="/trail_mini_cart.htm")
	@ResponseBody	
	public String trailMiniCart(String callback,String t){
		return "";
	}
	/**
	 * 首页点击迷你购物车删除按钮,返回jsonp
	 * @param callback =MiniCart.remove
	 * @param delId=117367657117 //cart_item_id
	 * @param t=1432888411956
	 * @returnMiniCart.remove(
		{
			"status" : true,
			"promotionCount" : 0,
            "quailtyTenseCount" : 0,
			"item" : [
							{ 
								"cartId": "116729300408", 
								"itemId": "44055305538",
								"title": "夏季透气网面男鞋气垫运动鞋男士休闲鞋韩版青春潮流板鞋单网潮鞋",
								"picUrl": "//img.taobaocdn.com/bao/uploaded/i1/1854798875/TB22U6vcFXXXXaEXpXXXXXXXXXX_!!1854798875.jpg_40x40.jpg",
								"price":"98.00",
								"sku" : ["颜色分类:276桔色"	,"尺码:40[新款抢购中！]"]
							},
							{ 
								"cartId": "116855977265", 
								"itemId": "44055305538", 
								"title": "夏季透气网面男鞋气垫运动鞋男士休闲鞋韩版青春潮流板鞋单网潮鞋", 
								"picUrl": "//img.taobaocdn.com/bao/uploaded/i2/1854798875/TB2afPucFXXXXbhXpXXXXXXXXXX_!!1854798875.jpg_40x40.jpg",
								"price":"98.00"	,
								"sku" : ["颜色分类:997灰色"	,"尺码:40[新款抢购中！]"]
							}
					],
			"num" : 2,
			"delCart" : 117367657117
		}
	)
	 */
	/**
	 * 在迷你购物车里删除某一个购物项
	 * @param callback
	 * @param delCart
	 * @param t
	 * @return
	 */
	@RequestMapping(value="/del_mini_cart.htm")
	@ResponseBody	
	public String delMiniCart(
		@RequestParam(value="callback")String callback,
		@RequestParam(value="del_id")String delCart,
		@RequestParam(value="t")String t){
		return "";
	}
}
