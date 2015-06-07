package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 子订单
 * @author Jerome
 *
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {


	private static final long serialVersionUID = -2706076358179172286L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private Long id;
	/** 商品标题 */
	@Column(length=50,nullable=false)
	private String itemTitle;
	/** 商品id */
	@Column(name="item_id",nullable=false)	
	private Long itemId;
	/** 商品价格 */
	@Column(nullable=false,precision=12,scale=2)
	private BigDecimal price ;
	/** 购买数量 */
	@Column(nullable=false)
	private Integer amount = 1;
	/**sku id 商品的最小库存单位Sku的id*/
	@Column(name="sku_id",nullable=false)
	private Long skuId;
	/** 子订单级订单优惠金额 **/
	@Column(name="discount_fee",nullable=false,precision=12,scale=2)
	private BigDecimal discountFee ;
	/** 手工调整金额 **/
	@Column(name="adjust_fee",precision=12,scale=2)
	private BigDecimal adjustFee;

	/** 应付金额（商品价格 * 购买数量 + 手工调整金额 - 子订单级订单优惠金额） */
	@Column(name="total_fee",nullable=false,precision=12,scale=2)
	private BigDecimal totalFee ;
	/** 子订单实付金额 **/
	@Column(nullable=false,precision=12,scale=2)
	private BigDecimal payment ;
	/** 订单状态 */
	@Enumerated(EnumType.STRING) 
	@Column(nullable=false)
	private OrderStatus status;
	/**退款状态 */
	@Enumerated(EnumType.STRING) 
	@Column(nullable=false)
	private RefundStatus refundStatus;
	/** 所属父订单 */
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
	@JoinColumn(name="trade_id")
	private Trade trade;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public BigDecimal getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}
	public BigDecimal getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(BigDecimal adjustFee) {
		this.adjustFee = adjustFee;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public RefundStatus getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(RefundStatus refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
