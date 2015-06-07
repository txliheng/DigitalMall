package edu.ccu.comp.se.digitalmall.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IItemCatDao;
import edu.ccu.comp.se.digitalmall.model.ItemCat;
@Repository
public class HibernateItemCatDao implements IItemCatDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public void save(ItemCat itemCat) {
		hibernateTemplate.save(itemCat);

	}
	private static final String FINDBYID_HQL="from ItemCat where id = ?";

	@SuppressWarnings("unchecked")
	@Override
	public ItemCat find(Long id) {
		List<ItemCat> list=hibernateTemplate.find(FINDBYID_HQL, id);
		if(list.size()>0)
			return (ItemCat) list.get(0);
		return null;		

	}

}
