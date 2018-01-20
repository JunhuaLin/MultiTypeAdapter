package cn.junhua.android.adapter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.TypeMatcher;

/**
 * MultiViewBinder consists of multiple ViewBinder
 */
public class MultiViewBinder<T> extends ViewBinder<T> {

    private Map<Class, ViewBinder<T>> mViewBinderManager;
    private TypeMatcher<T> mTypeMatcher;


    public MultiViewBinder(Class<T> beanClass, List<ViewBinder<T>> viewBinderList, TypeMatcher<T> matchViewBinder) {
        super(beanClass);
        mViewBinderManager = new HashMap<>(viewBinderList.size());
        for (ViewBinder<T> viewBinder : viewBinderList) {
            mViewBinderManager.put(viewBinder.getClass(), viewBinder);
        }
        mTypeMatcher = matchViewBinder;
    }

    @Override
    void setAdapter(MultiTypeAdapter adapter) {
        super.setAdapter(adapter);
        for (ViewBinder vb : mViewBinderManager.values()) {
            vb.setAdapter(adapter);
        }
    }

    @Override
    public int onCountView(T bean, int position) {
        return getViewBinder(bean, position).onCountView(bean, position);
    }

    @Override
    public int onCreateItemView(T bean, int position) {
        return getViewBinder(bean, position).onCreateItemView(bean, position);
    }

    @Override
    public void onBindView(ViewHolder holder, T bean, int position) {
        getViewBinder(bean, position).onBindView(holder, bean, position);
    }

    private ViewBinder<T> getViewBinder(T bean, int position) {
        Class<? extends ViewBinder<T>> viewBinderClass = mTypeMatcher.onMatch(bean, position);
        if (!mViewBinderManager.containsKey(viewBinderClass)) {
            throw new ViewBinderNotFoundException(bean.getClass(), viewBinderClass);
        }
        return mViewBinderManager.get(viewBinderClass);
    }
}
