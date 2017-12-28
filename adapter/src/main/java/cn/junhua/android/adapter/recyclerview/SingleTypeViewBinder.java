package cn.junhua.android.adapter.recyclerview;


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
    public int onCreateViewHolder(T bean, int position) {
        return mLayoutId;
    }

}
