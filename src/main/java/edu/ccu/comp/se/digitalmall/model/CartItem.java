package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 购物项
 */
@Entity
@Table(name="cart_item")
public class CartItem implements Serializable {


	private static final long serialVersionUID = 7902525188269273598L;
	/** 购物项id **/
	@Id @GeneratedValue
	private Long id;
	/** 所购商品sku **/
	@Column(nullable=false)
	private Long skuId;
	/** 所购商品 **/
	@Column(nullable=false)
	private Long itemId;
	/**  所属购物车  **/
/*    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	@JoinColumn(name="cart_id_fk")
	private MiniCart cart;*/
	/** 购买数量 **/
	@Column(nullable=false)
	private Integer num = 1;
	/**商品价格 **/
	@Column(nullable=false,precision=12,scale=2)
	private BigDecimal originPrice;
	/** 购物车里的商品单价，即促销价，如果没有促销活动，该价格即商品价格 **/
	@Column(nullable=false,precision=12,scale=2)
	private BigDecimal originPromo;
	/** sku属性名，比如"颜色分类:997灰色;尺码:40[新款抢购中！]" **/
	@Column(nullable=false)
	private String sku;
	/** 商品标题 **/
	@Column(nullable=false,length=80)
	private String title;
	/** 商品图片url **/
	@Column(nullable=false,length=228)
	private String picUrl;
	/** 买家昵称 **/
	@Column(nullable=false)
	private String nick;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getOriginPromo() {
		return originPromo;
	}
	public void setOriginPromo(BigDecimal originPromo) {
		this.originPromo = originPromo;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setSkus(List<String> skus) {
		this.skus = skus;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Transient
	private List<String> skus;
	
	public List<String> getSkus() {
		if(skus==null)
			skus=new ArrayList<String>();

		return skus;
	}
	public void addSkus(String sku){
		if(sku!=null){
			String[] skuProps=sku.split(";");
			for(String skuProp:skuProps){
				
				this.getSkus().add(skuProp);
			}
		}
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
		CartItem other = (CartItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
