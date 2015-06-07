package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 收货地址
 * @author Jerome
 *
 */
@Entity
@Table(name="delivery_address")
public class DeliveryAddress implements Serializable {

	private static final long serialVersionUID = -9114908873616230615L;
	@Id 
	@GeneratedValue
	@Column(name="address_id")
	private Long id;
	/** 邮政编码 **/
	@Column(length=6,nullable=false)
	private String zip;
	/**收货人全名 **/
	@Column(length=50,nullable=false)
	private String name;
	/** 固定电话 **/
	@Column(length=30)
	private String phone;
	/** 移动电话 **/
	@Column(length=30)
	private String mobile;
	/**收货详细地址 **/
	@Column(length=228,nullable=false)
	private String address;
	/**收货人所在地区 **/
	@Column(length=228,nullable=false)
	private String area;
	/**买家昵称 **/
	@Column(nullable=false)
	private String nick;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
}
