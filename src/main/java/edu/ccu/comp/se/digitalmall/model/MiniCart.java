package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*@Entity
@Table(name="mini_cart")*/
public class MiniCart implements Serializable {

	private static final long serialVersionUID = -3881879493857471651L;
	/** 购物车id **/
/*	@Id @GeneratedValue
	@Column(name="cart_id")
	private Long id;*/
	/** 购物项 **/
/*	@OneToMany( cascade=CascadeType.ALL,mappedBy="cart",fetch=FetchType.EAGER,targetEntity=CartItem.class)
	@Fetch(FetchMode.SELECT)
	@Basic(optional = true, fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)*/
	private List<CartItem> items;
	/** 购物车状态 **/
	/*@Column(nullable=false)	*/
	private Boolean status= Boolean.TRUE;
	/** 购物车所处购物阶段 **/
	/*@Column(nullable=false)*/
	private Integer period;
	/** 用户是否登录 **/
	/*@Column(name="is_login",nullable=false)*/
	private Boolean isLogin=Boolean.FALSE;
	/** 购物车降价商品的数量 **/
	/*@Column(name="promotion_Count",nullable=false)*/
	private Integer promotionCount=0;
	/** 购物车库存紧张的商品数量 */
	/*@Column(name="quailty_Tense_Count",nullable=false)*/
	private Integer quailtyTenseCount=0;
	/** 购物车总的商品数量 */
	/*@Column(nullable=false)*/
	private Integer num=0;
	/** 购物车所属用户昵称 */
	/*@Column(nullable=false)*/
	private String nick;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	public Integer getPromotionCount() {
		return promotionCount;
	}
	public void setPromotionCount(Integer promotionCount) {
		this.promotionCount = promotionCount;
	}
	public Integer getQuailtyTenseCount() {
		return quailtyTenseCount;
	}
	public void setQuailtyTenseCount(Integer quailtyTenseCount) {
		this.quailtyTenseCount = quailtyTenseCount;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public List<CartItem> getItems() {
		if(items == null)
			items = new ArrayList<CartItem>();
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}



}
