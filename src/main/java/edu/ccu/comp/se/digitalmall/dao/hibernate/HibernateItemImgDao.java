package edu.ccu.comp.se.digitalmall.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IItemtImgDao;
import edu.ccu.comp.se.digitalmall.model.ItemImg;
@Repository
public class HibernateItemImgDao implements IItemtImgDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Override
	public void save(ItemImg itemImg) {
		hibernateTemplate.save(itemImg);

	}

}
