package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品表(淘宝宝贝)
 * @author Jerome
 *
 */
@Entity
@Table(name="item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 2870430248489444234L;
	/** 商品id */
	@Id @GeneratedValue
	@Column(name="item_id")
	
	private Long id;
	/** 商品标题 **/
	@Column(length=50,nullable=false)
	private String title;

	/** 商品价格 **/
	@Column(name="price",nullable=false,precision=12,scale=2)
	private BigDecimal price;


	/** 商品介绍 **/
	@Lob @Column(nullable=false)
	private String descrption;

	/** 商品类目id.必须是叶子类目id **/
	@Column(name="cat_id")
	private Long catId;	
	
	/** 商品发布时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	/** 商品上传后的状态 **/
	@Enumerated(EnumType.STRING)
	@Column(name="approve_status")
	private ApproveStatus approveStatus=ApproveStatus.onsale;
	
	/** 商品数量 **/
	@Column(name="stock")
	private Integer qty;
	
	/** 商品销售数量 **/
	@Column(name="sell_qty")
	private Integer sellQty;	

	/** 商品sku **/
	@OneToMany( cascade=CascadeType.ALL,mappedBy="item",fetch=FetchType.EAGER,targetEntity=ItemSku.class)
	@Fetch(FetchMode.SELECT)
	@Basic(optional = true, fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Set<ItemSku> itemSkus = new HashSet<ItemSku>();
	
	/** 商品图片列表 **/
	@OneToMany( cascade=CascadeType.ALL,mappedBy="item",fetch=FetchType.EAGER,targetEntity=ItemImg.class)
	@Fetch(FetchMode.SELECT)
	@Basic(optional = true, fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	@OrderBy(value="position ASC")
	private List<ItemImg> itemImgs = new ArrayList<ItemImg>();

	/** 商品基本属性列表 **/
	@OneToMany( cascade=CascadeType.ALL,mappedBy="item",fetch=FetchType.EAGER,targetEntity=ItemPropValue.class)
	@Fetch(FetchMode.SELECT)
	@Basic(optional = true, fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Set<ItemPropValue> itemPropValues = new HashSet<ItemPropValue>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<ItemPropValue> getItemPropValues() {
		return itemPropValues;
	}
	public void setItemPropValues(Set<ItemPropValue> itemPropValues) {
		this.itemPropValues = itemPropValues;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public Set<ItemSku> getItemSkus() {
		return itemSkus;
	}
	public void setItemSkus(Set<ItemSku> itemSkus) {
		this.itemSkus = itemSkus;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	public ApproveStatus getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(ApproveStatus approveStatus) {
		this.approveStatus = approveStatus;
	}
	public Integer getSellQty() {
		return sellQty;
	}
	public void setSellQty(Integer sellQty) {
		this.sellQty = sellQty;
	}
	public List<ItemImg> getItemImgs() {
		return itemImgs;
	}
	public void setItemImgs(List<ItemImg> itemImgs) {
		this.itemImgs = itemImgs;
	}
	/**
	 * 添加图片到图片列表
	 * @param image
	 */
	public void addItemImage(ItemImg image){
		if(!this.itemImgs.contains(image)){
			this.itemImgs.add(image);
			image.setItem(this);
		}
	}
	public Map<String,Object> getSkuInfoMap(){
		Map<String,Object> skuInfoMap = new HashMap<String,Object>();
		skuInfoMap.put("skuMap", this.getSkuMap());
		skuInfoMap.put("item",this.getItemMap());
		skuInfoMap.put("prop",this.getSkuProps());
		skuInfoMap.put("success",Boolean.TRUE);

		return skuInfoMap;
	}
	/**
	 * 获取该商品的属性名列表
	 * @return
	 */
	public List<String> getPropNameList(){
		Iterator<ItemSku> it=this.getItemSkus().iterator();
		List<String> propNameList = new ArrayList<String>();
		if(it.hasNext()){
			String[] props=it.next().getPropsName().split(";");
			for(String prop:props){
				String[] propNameValues=prop.split(":");
				String propName=propNameValues[2];
				if(!propNameList.contains(propName)){
					propNameList.add(propName);
				}
			}	
		}
		return propNameList;
	}
	/**propsName 2:3:颜色:黑色;3:6:尺码:34
	 * @return
	 * {"尺　　码":{"20549:59280855":["36"],"20549:296172561":["35"],"20549:418624880":["39"],"20549:103189693":["38"],"20549:72380707":["37"]},
		"颜色分类":{"1627207:381956393":["豆沙灰色"],"1627207:93407511":["米杏色"],"1627207:28341":["黑色"],"1627207:30226":["浅粉色"]}}
	 */

	public Map<String,Object>getSkuProps(){
		List<String>propNameList = getPropNameList();
		Iterator<ItemSku> it=this.getItemSkus().iterator();
		Map<String,Object> skuPropsMap=new HashMap<String,Object>();
		for(String propName:propNameList){
			Map<String,String> pvidMap=new HashMap<String,String>();
			it=this.getItemSkus().iterator();
			while(it.hasNext()){
				String[] props=it.next().getPropsName().split(";");
				for(String prop:props){
					String[] propNameValues=prop.split(":");
					String pvid=propNameValues[0]+":"+propNameValues[1];
					String propValue=propNameValues[3];
					if(!pvidMap.containsKey(pvid)&&propName.equals(propNameValues[2]))					
						pvidMap.put(pvid, propValue);
				}		
			}
			skuPropsMap.put(propName, pvidMap);
		}
		return skuPropsMap;
	}
	/**@return
	 * {";20549:296172561;1627207:93407511;":{"stock":"997","price":"3960","skuId":"96955008246"},
	 *  ";20549:72380707;1627207:381956393;":{"stock":"944","price":"3960","skuId":"96955008253"},
	 *  ";20549:72380707;1627207:93407511;":{"stock":"954","price":"3960","skuId":"96955008248"}},
	 * 
	 */
	public Map<String,Object> getSkuMap(){
		Map<String,Object> skuMap=new HashMap<String,Object>();
		Set<ItemSku>  itemSkus=this.getItemSkus();
		if(itemSkus!=null){
			Iterator<ItemSku> it=itemSkus.iterator();
			while(it.hasNext()){
				ItemSku itemSku =it.next();
				Map<String,Object> sku=new HashMap<String,Object>();
				sku.put("stock", itemSku.getQuantity());
				sku.put("price", itemSku.getPrice());
				sku.put("skuId", itemSku.getId());
				skuMap.put(itemSku.getProps(),sku);
			}
		}		
		return skuMap;
	}
	/**
		@return "item":{"picUrl":"http://img03.taobaocdn.com/bao/uploaded/i3/TB1MFWqHpXXXXcIXXXXXXXXXXXX_!!0-item_pic.jpg","stock":"17701","price":"3960"}
	 */ 
	public Map<String,Object>getItemMap(){
		Map<String,Object> itemMap = new HashMap<String,Object>();
		List<ItemImg> itemImgs=this.getItemImgs();
		if(itemImgs!=null&&itemImgs.size()>0){
			itemMap.put("picUrl",itemImgs.get(0).getUrl());
		}
		else{
			itemMap.put("picUrl","");
		}
		itemMap.put("stock",this.getQty());
		itemMap.put("price",this.getPrice());
		return itemMap;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
