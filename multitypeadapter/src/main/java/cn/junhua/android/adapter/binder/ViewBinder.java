package cn.junhua.android.adapter.binder;

import android.content.Context;
import android.support.annotation.LayoutRes;

/**
 * the base class for binding view.
 * Created by junhua.lin on 2017/12/27.
 */
@SuppressWarnings("unchecked")
public abstract class ViewBinder<T> {

    private Class<T> mBeanClass;

    public ViewBinder(Class<T> clazz) {
        this.mBeanClass = clazz;
    }

    public final Class<T> getBeanClass() {
        return mBeanClass;
    }

    public Context getContext() {
        return null;
    }


    public int performCountView(Object bean, int position) {
        return onCountView((T) bean, position);
    }

    public int onCountView(T bean, int position) {
        return 6;
    }

    public @LayoutRes
    int performCreateItemView(Object bean, int position) {
        return onCreateItemView((T) bean, position);
    }

    public abstract @LayoutRes
    int onCreateItemView(T bean, int position);


    public void performBindView(ViewHolder holder, Object bean, int position) {
        onBindView(holder, (T) bean, position);
    }

    public abstract void onBindView(ViewHolder holder, T bean, int position);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewBinder<?> that = (ViewBinder<?>) o;

        return mBeanClass != null ? mBeanClass.equals(that.mBeanClass) : that.mBeanClass == null;
    }

    @Override
    public int hashCode() {
        return mBeanClass != null ? mBeanClass.hashCode() : 0;
    }
}
