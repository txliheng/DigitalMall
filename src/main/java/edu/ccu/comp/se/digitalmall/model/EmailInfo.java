package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="register_email")
public class EmailInfo implements Serializable {

	private static final long serialVersionUID = 839921559631266179L;
	/** 主键  */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="email_id")
	private Long id;
	/** 有效时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="validate_date",nullable=false)
	private Date validateDate;
	
	/**激活码 */
	@Column(name="validate_code",nullable=false,length=32)
	private String validateCode;
	
	/**是否激活 */
	@Column(nullable=false,length=1)
	private Boolean status=Boolean.FALSE;
	
	/**注册邮箱 */
	@Column(nullable=false,length=50)	
	private String email;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getValidateDate() {
		return validateDate;
	}
	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Transient
	private String from;
	@Transient
	private String[] to;
	@Transient
	private String[] cc;
	@Transient
	private String subject;
	@Transient
	private String templeteName;
	@Transient
	private String emailPlaceHolder;

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTempleteName() {
		return templeteName;
	}
	public void setTempleteName(String templeteName) {
		this.templeteName = templeteName;
	}
	public String getEmailPlaceHolder() {
		return emailPlaceHolder;
	}
	public void setEmailPlaceHolder(String emailPlaceHolder) {
		this.emailPlaceHolder = emailPlaceHolder;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((validateCode == null) ? 0 : validateCode.hashCode());
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
		EmailInfo other = (EmailInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (validateCode == null) {
			if (other.validateCode != null)
				return false;
		} else if (!validateCode.equals(other.validateCode))
			return false;
		return true;
	}
	
}
