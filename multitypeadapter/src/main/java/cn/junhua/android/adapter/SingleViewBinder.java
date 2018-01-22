package cn.junhua.android.adapter;


import android.support.annotation.LayoutRes;

/**
 * SingleViewBinder
 * Created by junhua.lin on 2017/12/28.
 */
public abstract class SingleViewBinder<T> extends ViewBinder<T> {
    private int mLayoutId;

    public SingleViewBinder(Class<T> beanClass, @LayoutRes int layoutId) {
        super(beanClass);
        this.mLayoutId = layoutId;
    }

    @Override
    public final @LayoutRes
    int onCreateItemView(T bean, int position) {
        return mLayoutId;
    }
}
