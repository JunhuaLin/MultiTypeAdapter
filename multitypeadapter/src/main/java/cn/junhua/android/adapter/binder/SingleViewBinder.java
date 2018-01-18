package cn.junhua.android.adapter.binder;


import cn.junhua.android.adapter.ViewBinder;

/**
 * SingleViewBinder
 * Created by junhua.lin on 2017/12/28.
 */
public abstract class SingleViewBinder<T> extends ViewBinder<T> {
    private int mLayoutId;

    public SingleViewBinder(Class<T> beanClass, int layoutId) {
        super(beanClass);
        this.mLayoutId = layoutId;
    }

    @Override
    public final int onCreateItemView(T bean, int position) {
        return mLayoutId;
    }
}
