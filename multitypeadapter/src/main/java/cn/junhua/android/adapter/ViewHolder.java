package cn.junhua.android.adapter;

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
 * the common ViewHolder
 */
@SuppressWarnings({"unused", "unchecked"})
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private RecyclerView mRecyclerView;

    public ViewHolder(View root, ViewGroup parent, int initialCapacity) {
        super(root);
        this.mViews = new SparseArray<>(initialCapacity >= 0 ? initialCapacity : 6);
        this.mConvertView = root;
        this.mRecyclerView = (RecyclerView) parent;
    }

    public <T extends View> T findView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public ViewHolder setText(@IdRes int viewId, String text) {
        TextView textView = findView(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = findView(viewId);
        textView.setText(resId);
        return this;
    }

    public ViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId) {
        ImageView imageView = findView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId) {
        findView(viewId).setBackgroundResource(drawableId);
        return this;
    }

}
