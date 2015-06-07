package edu.ccu.comp.se.digitalmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ccu.comp.se.digitalmall.dao.IItemtImgDao;
import edu.ccu.comp.se.digitalmall.model.ItemImg;
import edu.ccu.comp.se.digitalmall.service.IItemImgService;
@Service
public class ItemImgServiceImpl implements IItemImgService {

	@Autowired
	private IItemtImgDao itemImgDao;
	@Override
	public void save(ItemImg itemImg) {
		itemImgDao.save(itemImg);
		
	}

}
