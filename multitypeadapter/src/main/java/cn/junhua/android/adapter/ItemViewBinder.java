package cn.junhua.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * the base class for binding view.
 * Created by junhua.lin on 2017/12/27.
 */
public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

    //region filed
    MultiTypeAdapter adapter;

    //endregion

    //region public method
    public final MultiTypeAdapter getAdapter() {
        if (adapter == null) {
            throw new IllegalStateException(getClass().getName() + " not attached to MultiTypeAdapter. " +
                    "You should not call the method before registering the binder.");
        }
        return adapter;
    }

    public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    public void onBindViewHolder(VH holder, T bean, int position, List<Object> payloads) {
        onBindViewHolder(holder, bean, position);
    }

    public abstract void onBindViewHolder(VH holder, T bean, int position);
    //endregion

    //region ViewHolder common method
    protected long getItemId(T item, int position) {
        return RecyclerView.NO_ID;
    }

    protected void onViewRecycled(VH holder) {
    }

    protected boolean onFailedToRecycleView(VH holder) {
        return false;
    }

    protected void onViewAttachedToWindow(VH holder) {
    }

    protected void onViewDetachedFromWindow(VH holder) {
    }
    //endregion
}
