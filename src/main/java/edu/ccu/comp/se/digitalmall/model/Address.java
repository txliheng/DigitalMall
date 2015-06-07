package edu.ccu.comp.se.digitalmall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
/**
 * 地区字典表
 * @author Jerome
 *
 */
@Entity
@Table(name="address")
public class Address implements Serializable{

	private static final long serialVersionUID = 9118285665737522725L;
	@Id 
	@GeneratedValue
	private Long id;
	
	@Column(length=32,nullable=false)
	private String name;
	/**
     * 父路径
     *
     */
	@Column(name="parent_id",nullable=false)
	private Long parentId;
	/**
     * 所有父路径 如1,2,3,
     *
     */
	@Column(name = "parent_ids")
    private String parentIds;
	
    /**
     * 是否有叶子节点
     */
    @Formula(value = "(select count(*) from address addr where addr.parent_id = id)")
    private boolean hasChildren;
    
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
    public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }

     public String getSeparator() {
        return "/";
    }
     public boolean isRoot() {
         if (getParentId() != null && getParentId() == 0) {
             return true;
         }
         return false;
     }

     public boolean isLeaf() {
         if (isRoot()) {
             return false;
         }
         if (isHasChildren()) {
             return false;
         }

         return true;
     }	
}
