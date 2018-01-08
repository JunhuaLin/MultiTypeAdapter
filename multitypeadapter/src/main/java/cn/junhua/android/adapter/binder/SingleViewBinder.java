package cn.junhua.android.adapter.binder;


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
    public int onCreateItemView(T bean, int position) {
        return mLayoutId;
    }
}