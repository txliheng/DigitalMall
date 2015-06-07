package edu.ccu.comp.se.digitalmall.service;

import edu.ccu.comp.se.digitalmall.model.ItemCat;

public interface IItemCatService {

	/**
	 * 添加产品类别
	 * @param prodctType
	 */
	public void save(ItemCat itemCat);
	/**
	 * 修改产品类别
	 * @param productType
	 */
	public void update(ItemCat itemCat);
	/**
	 * 产品类别查询，按照类别id
	 * @param id
	 * @return
	 */
	public ItemCat find(Long id);
}
