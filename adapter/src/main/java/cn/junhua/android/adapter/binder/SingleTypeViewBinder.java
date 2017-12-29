package cn.junhua.android.adapter.binder;


/**
 * 单类型条目封装类
 */
public abstract class SingleTypeViewBinder<T> extends ViewBinder<T> {
    private int mLayoutId;

    public SingleTypeViewBinder(Class<T> beanClass, int layoutId) {
        super(beanClass);
        this.mLayoutId = layoutId;
    }

    @Override
    public int onCreateItemView(T bean, int position) {
        return mLayoutId;
    }
}
