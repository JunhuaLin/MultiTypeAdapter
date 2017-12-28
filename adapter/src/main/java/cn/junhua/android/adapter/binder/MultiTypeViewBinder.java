package cn.junhua.android.adapter.binder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.OnMatchViewBinder;

/**
 * 多类型条目包装类->多条目由多个SingleTypeViewBinder组成
 */
public class MultiTypeViewBinder<T> extends ViewBinder<T> {

    private Map<Class, ViewBinder<T>> mViewBinderManager;
    private OnMatchViewBinder<T> mOnMatchViewBinder;


    public MultiTypeViewBinder(Class<T> beanClass, List<ViewBinder<T>> viewBinderList, OnMatchViewBinder<T> matchViewBinder) {
        this(beanClass, viewBinderList, matchViewBinder, viewBinderList.size());
    }

    MultiTypeViewBinder(Class<T> beanClass, List<ViewBinder<T>> viewBinderList, OnMatchViewBinder<T> matchViewBinder, int initialCapacity) {
        super(beanClass);
        mViewBinderManager = new HashMap<>(initialCapacity);
        if (viewBinderList != null) {
            for (ViewBinder<T> viewBinder : viewBinderList) {
                mViewBinderManager.put(viewBinder.getClass(), viewBinder);
            }
        }
        mOnMatchViewBinder = matchViewBinder;
    }

    @Override
    public int onCreateViewHolder(T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = mOnMatchViewBinder.onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        return mViewBinderManager.get(viewBinderClass).performCreateViewHolder(bean, position);
    }

    @Override
    public void onBindView(ViewHolder holder, T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = mOnMatchViewBinder.onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        mViewBinderManager.get(viewBinderClass).performBindView(holder, bean, position);
    }
}
