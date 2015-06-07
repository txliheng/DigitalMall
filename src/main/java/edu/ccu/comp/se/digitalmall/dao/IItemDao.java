package edu.ccu.comp.se.digitalmall.dao;

import edu.ccu.comp.se.digitalmall.model.Item;

public interface IItemDao {

	public void save(Item item);
	public Item find(Long id);
}
