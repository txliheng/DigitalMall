package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 标准商品类目属性表
 * @author Jerome
 *
 */
@Entity
@Table(name="prop_name")
public class PropName implements Serializable{

	private static final long serialVersionUID = -3870322124703204811L;
	/** 属性名id **/
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pid")
	private Long id;

	/** 属性名 比如:颜色**/
	@Column(length=36)
	private String name;
	
	/** 属性值列表 **/
	@OneToMany(mappedBy="itemProp")
	private Set<CatPropValue> catPropValues=new HashSet<CatPropValue>();

	/** 是否关键属性 **/
	@Column(name="is_key_prop")
	private Boolean isKeyProp;
	
	/** 是否销售属性 **/
	@Column(name="is_sale_prop")
	private Boolean isSaleProp;

	/** 是否颜色属性 **/
	@Column(name="is_color_prop")
	private Boolean isColorProp;
	
	/** 是否枚举属性 **/
	@Column(name="is_enum_prop")
	private Boolean isEnumProp;
	
	/** 是否输入属性 在is_enum_prop是true的前提下，是否是卖家可以自行输入的属性 **/
	@Column(name="is_input_prop")
	private Boolean isInputProp;
	
	/** 是否必须 发布产品或商品时是否为必选属性 **/
	@Column
	private Boolean must;
	
	/** 发布产品或商品时是否可以多选 **/
	@Column
	private Boolean multi;
	
	/** 属性值类型 **/
	@Enumerated(EnumType.STRING)
	private PropValueType type;
	
	/** 排列序号。取值范围:大于零的整数**/
	@Column(name="sort_order")
	private Integer sortOrder;
	
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
	
	public Set<CatPropValue> getCatPropValues() {
		return catPropValues;
	}
	public void setCatPropValues(Set<CatPropValue> catPropValues) {
		this.catPropValues = catPropValues;
	}	
	
	public Boolean getIsKeyProp() {
		return isKeyProp;
	}
	public void setIsKeyProp(Boolean isKeyProp) {
		this.isKeyProp = isKeyProp;
	}
	public Boolean getIsSaleProp() {
		return isSaleProp;
	}
	public void setIsSaleProp(Boolean isSaleProp) {
		this.isSaleProp = isSaleProp;
	}
	public Boolean getIsColorProp() {
		return isColorProp;
	}
	public void setIsColorProp(Boolean isColorProp) {
		this.isColorProp = isColorProp;
	}
	public Boolean getIsEnumProp() {
		return isEnumProp;
	}
	public void setIsEnumProp(Boolean isEnumProp) {
		this.isEnumProp = isEnumProp;
	}
	public Boolean getIsInputProp() {
		return isInputProp;
	}
	public void setIsInputProp(Boolean isInputProp) {
		this.isInputProp = isInputProp;
	}
	public Boolean getMust() {
		return must;
	}
	public void setMust(Boolean must) {
		this.must = must;
	}
	public Boolean getMulti() {
		return multi;
	}
	public void setMulti(Boolean multi) {
		this.multi = multi;
	}
	public PropValueType getType() {
		return type;
	}
	public void setType(PropValueType type) {
		this.type = type;
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
		PropName other = (PropName) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
