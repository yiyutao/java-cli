package net.yiyutao.base;

import java.util.List;

/**
 * @author masterYI
 * Date: 2017/10/12
 * Time: 10:44
 * Description:
 */
public interface BaseMapper<T> {

    List<T> select(T record);

    int selectCount(T record);

    T selectByPrimaryKey(Object key);

    int insert(T record);

    int insertSelective(T record);

    int delete(T key);

    int deleteByPrimaryKey(Object key);

    int updateByPrimaryKey(Object key);

    int updateByPrimaryKeySelective(T record);

}
