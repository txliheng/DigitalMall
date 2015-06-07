package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 商品基本属性值表
 * @author Administrator
 *
 */
@Entity
@Table(name="item_prop_value")
public class ItemPropValue implements Serializable{

	private static final long serialVersionUID = -133552878786388752L;
	/**商品基本属性值id **/
	@Id @GeneratedValue
	@Column(name="item_attr_id")
	private Long id;
	/**商品id **/
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	@JoinColumn(name="item_id_fk")
	private Item item;
	/**属性名id **/
	@Column(name="pid")
	private Long propNameId;
	/**属性值id **/
	@Column(name="vid")
	private Long propValueId;
	/** 商品属性图片链接地址 格式http://host/image_path**/
	@Column(length=80)
	private String url;
	/** 是否图片属性 **/
	@Column(name="is_prop_img",length=1,nullable=false)
	private Boolean isPropImg = Boolean.FALSE;
	/**是否sku属性 **/
	@Column(name="is_sku",nullable=false)
	private Boolean isSku=Boolean.FALSE;
	/**sku id**/
	@Column(name="item_sku_id")
	private Long itemSkuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	public Long getPropNameId() {
		return propNameId;
	}
	public void setPropNameId(Long propNameId) {
		this.propNameId = propNameId;
	}
	public Long getPropValueId() {
		return propValueId;
	}
	public void setPropValueId(Long propValueId) {
		this.propValueId = propValueId;
	}
	public Boolean getIsSku() {
		return isSku;
	}
	public void setIsSku(Boolean isSku) {
		this.isSku = isSku;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getIsPropImg() {
		return isPropImg;
	}
	public void setIsPropImg(Boolean isPropImg) {
		this.isPropImg = isPropImg;
	}
	public Long getItemSkuId() {
		return itemSkuId;
	}
	public void setItemSkuId(Long itemSkuId) {
		this.itemSkuId = itemSkuId;
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
		ItemPropValue other = (ItemPropValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
