package edu.ccu.comp.se.digitalmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ccu.comp.se.digitalmall.dao.IItemCatDao;
import edu.ccu.comp.se.digitalmall.model.ItemCat;
import edu.ccu.comp.se.digitalmall.service.IItemCatService;
@Service
public class ItemCatServiceImpl implements IItemCatService {

	@Autowired
	IItemCatDao itemCatDao;
	@Override
	public void save(ItemCat itemCat) {
		
		itemCatDao.save(itemCat);
	}

	@Override
	public void update(ItemCat itemCat) {
		
		
	}

	@Override
	public ItemCat find(Long id) {
		
		return itemCatDao.find(id);
	}



}
