package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="cat_prop_value")
/**
 * 类目属性表
 * @author Jerome
 *
 */
public class CatPropValue implements Serializable {

	private static final long serialVersionUID = -260498685283386042L;

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne(optional=false)
	@Basic(optional=false)
	@JoinColumn(name="cid")
	private ItemCat itemCat;
	
	@ManyToOne(optional=false)
	@Basic(optional=false)
	@JoinColumn(name="pid")
	private PropName itemProp;
	
	@ManyToOne(optional=true)
	@Basic(optional=true)
	@JoinColumn(name="vid")
	private PropValue propValue;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private CatPropValue parent;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ItemCat getItemCat() {
		return itemCat;
	}
	public void setItemCat(ItemCat itemCat) {
		this.itemCat = itemCat;
	}
	public PropName getItemProp() {
		return itemProp;
	}
	public void setItemProp(PropName itemProp) {
		this.itemProp = itemProp;
	}
	public PropValue getPropValue() {
		return propValue;
	}
	public void setPropValue(PropValue propValue) {
		this.propValue = propValue;
	}

	public CatPropValue getParent() {
		return parent;
	}
	public void setParent(CatPropValue parent) {
		this.parent = parent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemCat == null) ? 0 : itemCat.hashCode());
		result = prime * result
				+ ((itemProp == null) ? 0 : itemProp.hashCode());
		result = prime * result
				+ ((propValue == null) ? 0 : propValue.hashCode());
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
		CatPropValue other = (CatPropValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemCat == null) {
			if (other.itemCat != null)
				return false;
		} else if (!itemCat.equals(other.itemCat))
			return false;
		if (itemProp == null) {
			if (other.itemProp != null)
				return false;
		} else if (!itemProp.equals(other.itemProp))
			return false;
		if (propValue == null) {
			if (other.propValue != null)
				return false;
		} else if (!propValue.equals(other.propValue))
			return false;
		return true;
	}

	
}
