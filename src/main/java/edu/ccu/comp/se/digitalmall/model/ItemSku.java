package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * 商品sku表(库存表)
 * @author Administrator
 *
 */
@Entity
@Table(name="item_sku")
public class ItemSku implements Serializable{

	private static final long serialVersionUID = -7055497728934025196L;
	/** sku id **/
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sku_id")
	private Long id;
	/** sku所对应的销售属性的中文名字串 格式如：pid1:vid1:pid_name1:vid_name1;pid2:vid2:pid_name2:vid_name2**/
	@Column(name="props_name")
	private String propsName;
	/** 所属商品 **/
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	@JoinColumn(name="item_id_fk")
	private Item item;
	/** sku的销售属性组合字符串(颜色，大小，等等),格式是pid1:vid1;pid2:vid2 **/
	@Column(nullable=false)
	private String props;
	
	/** 属于这个sku的商品的价格 **/
	@Column(nullable=false)
	private BigDecimal price;
	/** 属于这个sku的商品的数量 **/
	@Column(nullable=false)
	private Integer quantity;
	/** 条形码 **/	
	private String barCode;
	/** sku状态 normal:正常 ；delete:删除 **/
	@Enumerated(EnumType.STRING)
	private ItemStatus status=ItemStatus.normal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPropsName() {
		return propsName;
	}
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public ItemStatus getStatus() {
		return status;
	}
	public void setStatus(ItemStatus status) {
		this.status = status;
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
		ItemSku other = (ItemSku) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
