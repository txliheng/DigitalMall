package edu.ccu.comp.se.digitalmall.service;

//import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import edu.ccu.comp.se.digitalmall.model.MiniCart;
import edu.ccu.comp.se.digitalmall.model.CartItem;
import edu.ccu.comp.se.digitalmall.model.Item;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class FastJsonTest {

	@Autowired
	IItemService itemService;
	@Test
	@Transactional
	public void testGetSkuInfo() {
		Item item = itemService.find(new Long(1L));
		if(item != null){
			System.out.println(JSON.toJSONString(item.getSkuInfoMap()));
		}
	}
	@Test
	public void testGetCartInfo(){
		MiniCart cart=new MiniCart();
		cart.setStatus(Boolean.TRUE);
		cart.setPeriod(3);
		cart.setIsLogin(Boolean.TRUE);
		CartItem item=new CartItem();
		item.setId(117367657117L);
		item.setItemId(18049865645L);
		item.setTitle("B26 奢华进口水钻水晶方钻满天星手表女白色大表盘时装表正品包邮");
		item.setPicUrl("//img.taobaocdn.com/bao/uploaded/i3/18918034524184091/T1WQifXthgXXXXXXXX_!!1-item_pic.gif_40x40.jpg");
		item.setOriginPrice(new BigDecimal("388.00"));
		item.setOriginPromo(new BigDecimal("98.00"));
		item.setSku("颜色分类:276桔色;尺码:40[新款抢购中！]");
		item.addSkus(item.getSku());
		item.setSku(null);
		cart.getItems().add(item);
		cart.setNum(3);
		System.out.println(JSON.toJSONString(cart));
		
	}
}
