package edu.ccu.comp.se.digitalmall.service;

import edu.ccu.comp.se.digitalmall.model.Item;

public interface IItemService {

	/**
	 * 发布商品
	 * @param item
	 */
	public void save(Item item);
	/**
	 * 查看商品详情，根据商品id查询
	 * @param id
	 * @return
	 */
	public Item find(Long id);


}
