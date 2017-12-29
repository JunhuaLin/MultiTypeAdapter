package cn.junhua.android.adapter.binder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.OnMatchListener;

/**
 * 多类型条目包装类->多条目由多个SingleTypeViewBinder组成
 */
public class MultiTypeViewBinder<T> extends ViewBinder<T> {

    private Map<Class, ViewBinder<T>> mViewBinderManager;
    private OnMatchListener<T> mOnMatchListener;


    public MultiTypeViewBinder(Class<T> beanClass, List<ViewBinder<T>> viewBinderList, OnMatchListener<T> matchViewBinder) {
        super(beanClass);
        mViewBinderManager = new HashMap<>(viewBinderList.size());
        for (ViewBinder<T> viewBinder : viewBinderList) {
            mViewBinderManager.put(viewBinder.getClass(), viewBinder);
        }
        mOnMatchListener = matchViewBinder;
    }

    @Override
    public int onCountView(T bean, int position) {
        return getViewBinder(bean, position).performCountView(bean, position);
    }

    @Override
    public int onCreateItemView(T bean, int position) {
        return getViewBinder(bean, position).performCreateItemView(bean, position);
    }

    @Override
    public void onBindView(ViewHolder holder, T bean, int position) {
        getViewBinder(bean, position).performBindView(holder, bean, position);
    }

    private ViewBinder<T> getViewBinder(T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = mOnMatchListener.onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        return mViewBinderManager.get(viewBinderClass);
    }
}
