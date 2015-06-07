package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 交易单(父订单)
 * @author Jerome
 *
 */
@Entity
@Table(name="trade")
public class Trade implements Serializable {

	private static final long serialVersionUID = 1712711472228256989L;
	/** 父订单的交易号 */
	@Id @GeneratedValue @Column(length=16)
	private Long id;
	/** 交易创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(nullable=false)
	private Date created = new Date();
	/**付款时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="pay_time")
	private Date payTime;
	/** 交易结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="end_time")	
	private Date endTime;
	/**卖家发货时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="consign_time")
	private Date consignTime;
	/** 交易状态 */
	@Enumerated(EnumType.STRING) 
	@Column(nullable=false)
	private OrderStatus status;
	/** 实付金额 **/
	@Column(nullable=false,precision=12,scale=2)
	private BigDecimal payment ;
	/** 系统优惠金额 **/
	@Column(name="discount_fee",nullable=false,precision=12,scale=2)
	private BigDecimal discountFee ;
	/** 商品金额（商品价格乘以数量的总金额） */
	@Column(name="total_fee",nullable=false,precision=12,scale=2)
	private BigDecimal totalFee ;
	/** 邮费 **/
	@Column(name="post_fee",nullable=false,precision=12,scale=2)
	private BigDecimal postFee ;
	/**是否包含邮费 **/
	@Column(name="has_post_fee",nullable=false)
	private Boolean hasPostFee=Boolean.TRUE;
	/** 买家留言 **/
	@Column(length=100,name="buyer_message")
	private String buyerMessage;
	/** 买家货到付款服务费 **/
	@Column(name="buyer_cod_fee",precision=12,scale=2)
	private BigDecimal buyerCodFee;
	/** 卖家手工调整金额 **/
	@Column(name="adjust_fee",precision=12,scale=2)
	private BigDecimal adjustFee;
	/**交易标题，以店铺名作为此标题的值 **/
	@Column(nullable=false)
	private String title;
	/**物流方式 **/
	@Column(name="shipping_type",nullable=false)
	private DeliveryWay deliveryWay=DeliveryWay.free;
    /** 收货地址 **/
	@Column(name="delivery_address_id",nullable=false)
	private Long deliveryAddressId;
	/** 买家昵称 **/
	@Column(name="buyer_nick",nullable=false)
	private String buyerNick;
	/** 卖家昵称 **/
	@Column(name="seller_nick",nullable=false)
	private String sellerNick;
	/** 子订单 **/
	@OneToMany(mappedBy="trade",cascade=CascadeType.ALL)
	private List<Order> orders = new ArrayList<Order>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getConsignTime() {
		return consignTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public BigDecimal getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getPostFee() {
		return postFee;
	}
	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}
	public Boolean getHasPostFee() {
		return hasPostFee;
	}
	public void setHasPostFee(Boolean hasPostFee) {
		this.hasPostFee = hasPostFee;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public BigDecimal getBuyerCodFee() {
		return buyerCodFee;
	}
	public void setBuyerCodFee(BigDecimal buyerCodFee) {
		this.buyerCodFee = buyerCodFee;
	}
	public BigDecimal getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(BigDecimal adjustFee) {
		this.adjustFee = adjustFee;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DeliveryWay getDeliveryWay() {
		return deliveryWay;
	}
	public void setDeliveryWay(DeliveryWay deliveryWay) {
		this.deliveryWay = deliveryWay;
	}
	public Long getDeliveryAddressId() {
		return deliveryAddressId;
	}
	public void setDeliveryAddressId(Long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getSellerNick() {
		return sellerNick;
	}
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	/**
	 * 添加子订单
	 * @param order
	 */
	public void addOrder(Order order){
		this.orders.add(order);
		order.setTrade(this);
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
		Trade other = (Trade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
