package edu.ccu.comp.se.digitalmall.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ccu.comp.se.digitalmall.model.ItemImg;
import edu.ccu.comp.se.digitalmall.model.Item;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class ItemServiceTest {

	@Autowired
	IItemService itemService;
	@Autowired
	IItemCatService itemCatService;
	@Autowired
	IItemImgService itemImgService;
	@Test
	@Transactional
	public void testSave() {
		Item item=new Item();
/*		item.setTitle("第二批 5月9号10点78元包邮 芊洛-dc71 衬衫+格子裙 时尚两件套");
		item.setDescrption("厚度薄，无内衬， 两件套合身版型，上衣为娃 娃领衬衫，青春活力的娃娃领，彰显 年轻时尚风度，领部波浪包边，凸显甜美气 质，后背隐形拉链收襟，彰显简约小性感，无袖 更显女人味，展示浪漫气质，前身个性的压褶条，增 加层次感，衣身纯色与格子图案的撞色，在视觉上醒目， 凸显独特之处，下装为半身");
		item.setPrice(156.00F);
		item.setShopPrice(78.00F);
		item.setCatId(2L);*/
		item.setTitle("白领丽人2015春新款优质羊皮尖头粗跟甜美蝴蝶结浅口女单鞋3339Q");
		item.setDescrption("【女神节品牌团】春季新品惊艳首聚，聚价298元，【尊享顺丰包邮】3月6日0点开抢，【活动时间：3月6日-3月8日】疯抢3天！");
		item.setPrice(new BigDecimal("988.00"));
		item.setCatId(5L);

		List<String> imgList=getImgs();
		 
		for(int j=0;j<imgList.size();j++){
			ItemImg itemImg = new ItemImg();
			itemImg.setUrl(imgList.get(j));
			item.addItemImage(itemImg);
		}
		itemService.save(item);

	}
	private List<String> getImgs() {  
		List<String> list = new ArrayList<String>(); 
		
		for(int i = 0;i < 2;i++){
			String imageFilename  = UUID.randomUUID().toString()+"_!!0-item_pic"+".jpg";
			list.add(imageFilename);
			String imageFilename_400x400 = imageFilename + "_400x400.jpg";
			list.add(imageFilename_400x400);
			String imageFilename_50x50 = imageFilename + "_50x50.jpg";
			list.add(imageFilename_50x50);
		}
	   
	    return list;
	} 

	@Test
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public void testFind(){
		Item item=itemService.find(1L);
		Assert.assertNotNull(item);

	}
}
