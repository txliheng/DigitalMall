package edu.ccu.comp.se.digitalmall.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IItemSkuDao;
import edu.ccu.comp.se.digitalmall.model.ItemSku;
@Repository
public class HibernateItemSkuDao implements IItemSkuDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	private static final String FINDBYID_HQL="from ItemSku where id = ?";

	@SuppressWarnings("unchecked")
	@Override
	public ItemSku find(Long id) {
		List<ItemSku>list=hibernateTemplate.find(FINDBYID_HQL, id);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
