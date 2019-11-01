package cn.junhua.android.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * the common view holder
 */
@SuppressWarnings({"unused", "unchecked"})
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private RecyclerView mRecyclerView;

    public CommonViewHolder(View root, ViewGroup parent, int initialCapacity) {
        super(root);
        this.mViews = new SparseArray<>(initialCapacity >= 0 ? initialCapacity : 6);
        this.mRecyclerView = (RecyclerView) parent;
    }

    public <T extends View> T findView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public CommonViewHolder setText(@IdRes int viewId, String text) {
        TextView textView = findView(viewId);
        textView.setText(text);
        return this;
    }

    public CommonViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = findView(viewId);
        textView.setText(resId);
        return this;
    }

    public CommonViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId) {
        ImageView imageView = findView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public CommonViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId) {
        findView(viewId).setBackgroundResource(drawableId);
        return this;
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public Resources getResources() {
        return itemView.getResources();
    }

}
