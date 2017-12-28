package cn.junhua.android.adapter.recyclerview;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;

/**
 * 多类型条目包装类->多条目由多个SingleTypeViewBinder组成
 */
public abstract class MultiTypeViewBinder<T> extends ViewBinder<T> {

    private Map<Class, ViewBinder<T>> mViewBinderManager;


    public MultiTypeViewBinder(Class<T> beanClass) {
        this(beanClass, null);
    }

    public MultiTypeViewBinder(Class<T> beanClass, List<ViewBinder<T>> viewBinderList) {
        super(beanClass);
        mViewBinderManager = new HashMap<>(initialCapacity());
        if (viewBinderList != null) {
            for (ViewBinder<T> viewBinder : viewBinderList) {
                addViewHinder(viewBinder);
            }
        }
    }

    public void addViewHinder(ViewBinder<T> viewBinder) {
        mViewBinderManager.put(viewBinder.getClass(), viewBinder);
    }

    @Override
    public int onCreateViewHolder(T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        return mViewBinderManager.get(viewBinderClass).performCreateViewHolder(bean, position);
    }

    @Override
    public void onBindView(ViewHolder holder, T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        mViewBinderManager.get(viewBinderClass).performBindView(holder, bean, position);
    }

    public abstract Class<? extends ViewBinder<T>> onMatch(T bean, int position);

    public int initialCapacity() {
        return 3;
    }
}
