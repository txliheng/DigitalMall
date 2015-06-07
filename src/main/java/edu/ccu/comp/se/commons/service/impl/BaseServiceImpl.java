package edu.ccu.comp.se.commons.service.impl;

import java.util.List;

import edu.ccu.comp.se.commons.PageConstants;
import edu.ccu.comp.se.commons.dao.IBaseDao;
import edu.ccu.comp.se.commons.pagination.Page;
import edu.ccu.comp.se.commons.pagination.PageUtil;
import edu.ccu.comp.se.commons.service.IBaseService;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK> {
    
    protected IBaseDao<M, PK> dao;
    
    public void setDao(IBaseDao<M, PK> dao) {
        this.dao = dao;
    }
    
    public IBaseDao<M, PK> getDao() {
        return this.dao;
    }

    @Override
    public M save(M model) {
        getDao().save(model);
        return model;
    }
    
    @Override
    public void merge(M model) {
        getDao().merge(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getDao().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getDao().update(model);
    }
    
    @Override
    public void delete(PK id) {
        getDao().delete(id);
    }

//    @Override
//    public void deleteObject(M model) {
//        getDao().deleteObject(model);
//    }

    @Override
    public M get(PK id) {
        return getDao().get(id);
    }

   
    
    @Override
    public int countAll() {
        return getDao().countAll();
    }
    
    @Override
    public List<M> listAll() {
        return getDao().listAll();
    }
    
    @Override
    public Page<M> listAll(int pn) {
        return this.listAll(pn, PageConstants.DEFAULT_PAGE_SIZE);
    }
    
    @Override
    public Page<M> listAll(int pn, int pageSize) {
        Integer count = countAll();
        List<M> items = getDao().listAll(pn, pageSize);
        return PageUtil.getPage(count, pn, items, pageSize);
    }
    
}
