package cn.junhua.android.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

/**
 * the base class for binding view.
 * Created by junhua.lin on 2017/12/27.
 */
public abstract class ViewBinder<T> {

    private Class<? extends T> mBeanClass;
    private MultiTypeAdapter adapter;

    public ViewBinder(Class<? extends T> clazz) {
        this.mBeanClass = clazz;
    }

    public final MultiTypeAdapter getAdapter() {
        if (adapter == null) {
            throw new IllegalStateException(getClass().getName() + " not attached to MultiTypeAdapter. " +
                    "You should not call the method before registering the binder.");
        }
        return adapter;
    }

    void setAdapter(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    final Class<? extends T> getBeanClass() {
        return mBeanClass;
    }

    public int onCountView(T bean, int position) {
        return 6;
    }

    /**
     * @return layout id
     */
    public abstract @LayoutRes
    int onCreateItemView(T bean, int position);

    public abstract void onBindView(ViewHolder holder, T bean, int position);

    protected long getItemId(int position) {
        return RecyclerView.NO_ID;
    }

    protected void onViewRecycled(ViewHolder holder) {
    }

    protected boolean onFailedToRecycleView(ViewHolder holder) {
        return false;
    }

    protected void onViewAttachedToWindow(ViewHolder holder) {
    }

    protected void onViewDetachedFromWindow(ViewHolder holder) {
    }

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
