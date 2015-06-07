package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 属性值表
 * @author Administrator
 *
 */
@Entity
@Table(name="prop_value")
public class PropValue implements Serializable{

	private static final long serialVersionUID = 2828219420712009446L;
	/** 属性值id **/
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vid")
	private Long id;
	
	/** 属性列表 **/
	@OneToMany(mappedBy="propValue")
	private Set<CatPropValue> catPropValues=new HashSet<CatPropValue>();

	/** 属性值 比如:军绿色 **/
	@Column
	private String name;
	


	/** 排列序号。取值范围:大于零的整数**/
	@Column(name="sort_order")
	private Integer sortOrder;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Set<CatPropValue> getCatPropValues() {
		return catPropValues;
	}
	public void setCatPropValues(Set<CatPropValue> catPropValues) {
		this.catPropValues = catPropValues;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
		PropValue other = (PropValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
