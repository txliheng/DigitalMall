package edu.ccu.comp.se.digitalmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ccu.comp.se.digitalmall.dao.IItemSkuDao;
import edu.ccu.comp.se.digitalmall.model.ItemSku;
import edu.ccu.comp.se.digitalmall.service.IItemSkuService;
@Service
public class ItemSkuServiceImpl implements IItemSkuService {

	@Autowired
	IItemSkuDao itemSkuDao;
	@Override
	public ItemSku find(Long itemId, String props) {
		
		return null;
	}

	@Override
	public ItemSku find(Long itemSkuId) {
		
		return itemSkuDao.find(itemSkuId);
	}

}
