package com.mcoding.base.core;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/6/4.
 */
public interface BaseService<T extends Serializable, Example extends IExample<T>> {
    public void addObj(T t);

    public void deleteObjById(int id);

    public void modifyObj(T t);

    public T queryObjById(int id);
    
    public List<T> queryAllObjByExample(Example example);

    public PageView<T> queryObjByPage(Example example);
}
