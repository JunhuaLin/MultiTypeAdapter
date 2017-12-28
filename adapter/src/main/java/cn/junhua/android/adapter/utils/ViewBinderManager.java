package cn.junhua.android.adapter.utils;


import java.util.Collection;
import java.util.HashMap;

import cn.junhua.android.adapter.binder.ViewBinder;

/**
 * ViewBinder的列表管理器
 *
 * @author lin
 *         <p/>
 *         2016年6月6日上午11:26:57
 */
public class ViewBinderManager extends HashMap<Class<?>, ViewBinder> {

    /**
     *
     */
    private static final long serialVersionUID = -7149248838995627451L;

    /**
     * 添加ViewBinder
     */
    public boolean add(ViewBinder viewBinder) {
        if (viewBinder == null) {
            return false;
        }
        //每类布局只能对应一种绑定数据的方式,重复添加会被覆盖
        return put(viewBinder.getBeanClass(), viewBinder) != null;
    }

    public boolean addAll(Collection<? extends ViewBinder> collection) {
        boolean b = true;
        for (ViewBinder vb : collection) {
            b &= add(vb);
        }
        return b;
    }

    public ViewBinder remove(ViewBinder viewBinder) {
        return super.remove(viewBinder.getBeanClass());
    }
}