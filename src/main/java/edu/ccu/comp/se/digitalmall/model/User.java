package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="user",
uniqueConstraints = {@UniqueConstraint(columnNames={"email"}),@UniqueConstraint(columnNames={"nick"})}
)
public class User implements Serializable{
    public static final String NICK_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    public static final String MOBILE_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;

	private static final long serialVersionUID = 9089267909364601413L;
	/**主键	*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**密码  */
    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = "{user.password.not.valid}")
	private String password;//MD5加密

    /**会员名/昵称  */
	@NotNull(message = "{not.null}")
    @Pattern(regexp = NICK_PATTERN, message = "{user.nick.not.valid}")
	private String nick;
	
	/** 性别 **/
	@Enumerated(EnumType.STRING)
	private Sex sex= Sex.male;
	
	/** 注册时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();
	
	
	/** 用户状态 **/
	@Enumerated(EnumType.STRING)
	private UserStatus status =  UserStatus.normal;
	
	/** 买家默认收货地址 **/
	@Column(name="delivery_address_id")
	private Long deliveryAddressId = 0L;
	
	/**电子邮箱  */
    @Pattern(regexp = EMAIL_PATTERN, message = "{user.email.not.valid}")
	private String email;
	
    /**移动电话  */
    @NotEmpty(message = "{not.null}")
    @Pattern(regexp = MOBILE_PATTERN, message = "{user.mobile.not.valid}")
	private String mobile;


	public Long getUserId() {
		return id;
	}
	public void setUserId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public Long getDeliveryAddressId() {
		return deliveryAddressId;
	}
	public void setDeliveryAddressId(Long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
