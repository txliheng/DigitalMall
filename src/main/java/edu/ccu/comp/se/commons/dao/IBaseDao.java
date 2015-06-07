package edu.ccu.comp.se.commons.dao;

import java.io.Serializable;
import java.util.List;
/**
 * 通用dao接口
 * @author jerome
 *
 * @param <M>模型对象
 * @param <PK>主键
 */

public interface IBaseDao<M extends Serializable,PK extends Serializable> {
    /**
     * 保存模型对象
     * @param model 模型对象
     * @return 返回主键
     */
    public void save(M model);

    /**
     * 保存或更新模型对象
     * @param model 模型对象
     */
    public void saveOrUpdate(M model);
    
    /**
     * 更新模型对象
     * @param model 模型对象
     */
    public void update(M model);
    
    /**
     * 
     * 合并模型对象状态到底层会话
     * @param model
     */
    public void merge(M model);
    
    /**
     * 删除模型对象
     * @param id 主键
     */
    public void delete(PK id);
    
//    /**
//     * 删除模型对象
//     * @param model 模型对象
//     */
//    public void deleteObject(M model);
    
    /**
     * 根据主键获取模型对象
     * @param id 主键
     * @return 返回模型对象
     */
    public M get(PK id);
    
    /**
     * 统计模型对象对应数据库表中的记录数
     * @return 返回数据库表的记录数
     */
    public int countAll();
    
    /**
     * 
     * @return 返回所有模型对象
     */
    public List<M> listAll();

    /**
     * 分页获取所有模型对象
     * @param pn 页码 从1开始
     * @param pageSize 每页记录数
     * @return
     */
    public List<M> listAll(int pn, int pageSize);
    
//    /**
//     * 是否存在指定主键的记录
//     * @param id 主键
//     * @return true表示存在相应记录，false表示不存在
//     */
//    boolean exists(PK id);
    
//    /**
//     * 刷新底层session对象（可能对部分实现无效如JDBC）
//     */
//    public void flush();
//    
//    /**
//     * 执行底层session对象的clear操作（可能对部分实现无效如JDBC）
//     */
//    public void clear();
}
