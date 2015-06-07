package edu.ccu.comp.se.digitalmall.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IItemDao;
import edu.ccu.comp.se.digitalmall.model.Item;

@Repository
public class HibernateItemDao implements IItemDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public void save(Item item) {
		hibernateTemplate.save(item);
		
	}
	private static final String FINDBYID_HQL="from Item where id = ?";

	@SuppressWarnings("unchecked")
	@Override
	public Item find(Long id) {
		List<Item> list=hibernateTemplate.find(FINDBYID_HQL, id);
		if(list.size()>0)
			return (Item) list.get(0);
		return null;	
	}

}
