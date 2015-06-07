package edu.ccu.comp.se.digitalmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ccu.comp.se.digitalmall.dao.IItemDao;
import edu.ccu.comp.se.digitalmall.model.Item;
import edu.ccu.comp.se.digitalmall.service.IItemService;
@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	IItemDao itemDao;
	@Override
	public void save(Item item) {
		itemDao.save(item);

	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Item find(Long id) {
		
		return itemDao.find(id);
	}




}
