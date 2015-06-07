package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
/**
 * 商品类目表
 * @author Administrator
 *
 */
@Entity
@Table(name="item_cat")
public class ItemCat implements Serializable {

	private static final long serialVersionUID = -2344877039830101207L;
	/** 商品所属类目id **/
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cid")
	private Long id;

	/** 类目名称 **/
	@Column(length=36,nullable=false)
	private String name;
	
	/** 该类目是否为叶子类目(即：该类目是否还有子类目) **/
	@Formula(value="(select count(*) from item_cat i_c where i_c.parent_cid_fk = cid)")
	private Boolean hasChildren;

	/** 子类目 **/
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	private Set<ItemCat> childCats = new HashSet<ItemCat>();
	
	/** 父类目id 父类目id=null时，代表的是一级的类目**/
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parent_cid_fk")
	 ItemCat parent;
	
	/** 叶子类目属性 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="itemCat",fetch=FetchType.EAGER,targetEntity=CatPropValue.class)
	@Fetch(FetchMode.SELECT)
	@Basic(optional = true, fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Set<CatPropValue> itemPropValues ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<ItemCat> getChildCats() {
		return childCats;
	}
	public void setChildCats(Set<ItemCat> childCats) {
		this.childCats = childCats;
	}
	public ItemCat getParent() {
		return parent;
	}
	public void setParent(ItemCat parent) {
		this.parent = parent;
	}

	public Set<CatPropValue> getItemPropValues() {
		if(itemPropValues == null)
			itemPropValues = new HashSet<CatPropValue>();
		return itemPropValues;
	}
	public void setItemPropValues(Set<CatPropValue> itemPropValues) {
		this.itemPropValues = itemPropValues;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
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
		ItemCat other = (ItemCat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
