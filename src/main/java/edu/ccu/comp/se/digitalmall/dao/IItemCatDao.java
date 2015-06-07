package edu.ccu.comp.se.digitalmall.dao;

import edu.ccu.comp.se.digitalmall.model.ItemCat;

public interface IItemCatDao {

	public void save(ItemCat itemCat);
	public ItemCat find(Long id);
}
