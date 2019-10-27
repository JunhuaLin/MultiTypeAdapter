package cn.junhua.android.adapter;


import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SimpleItemViewBinder
 * Created by junhua.lin on 2017/12/28.
 */
public abstract class SimpleItemViewBinder<T> extends ItemViewBinder<T, CommonViewHolder> {

    private int initialCapacity = 6;

    @Override
    public CommonViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View root = inflater.inflate(getLayoutId(), parent, false);
        return new CommonViewHolder(root, parent, initialCapacity);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }
}
