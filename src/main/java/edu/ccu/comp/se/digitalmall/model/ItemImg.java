package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * 商品图片表
 * @author Administrator
 *
 */
@Entity
@Table(name="item_img")
public class ItemImg implements Serializable {


	private static final long serialVersionUID = 821061888755683390L;
	/** 商品图片id 主图id默认为1**/
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="item_img_id")
	private Long id;
	/** 图片链接地址 绝对地址,格式http://img03.taobao.net/bao/uploaded/i3/T1HXdXXgPSt0JxZ2.8_070458.jpg**/
	@Column(name="url",length=80,nullable=false)
	private String url;
	/** 图片所属商品id **/
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	@JoinColumn(name="item_id_fk")
	private Item item;
	/** 图片序号 图片放在第几张（多图时可设置）**/
	@Column(nullable=false)
	private Integer position;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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
		ItemImg other = (ItemImg) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
