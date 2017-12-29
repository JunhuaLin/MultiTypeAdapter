package cn.junhua.android.adapter.binder;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用ViewHolder
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    /**
     * 传入复用对象
     *
     * @param root            parent -> RecyclerView.onCreateItemView(ViewGroup parent, int viewType)
     * @param initialCapacity 缓存的初始大小
     */
    public ViewHolder(View root, int initialCapacity) {
        super(root);
        this.mViews = new SparseArray<>(initialCapacity >= 0 ? initialCapacity : 6);
        this.mConvertView = root;
    }

    /**
     * 查找View只会执行一次findViewById方法，之后每次都从缓存中获取View
     *
     * @param viewId the view's id
     * @return T extends View
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T findView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 便捷设置TextView的内容
     *
     * @param viewId TextView的id
     * @param text   TextView的内容
     * @return ViewHolder
     */
    public ViewHolder setText(@IdRes int viewId, String text) {
        TextView textView = findView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 便捷设置TextView的内容
     *
     * @param viewId TextView的id
     * @param resId  字符串资源id
     * @return ViewHolder
     */
    public ViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = findView(viewId);
        textView.setText(resId);
        return this;
    }

    /**
     * 便捷设置ImageView的内容
     *
     * @param imageViewId ImageView的id
     * @param drawableId  ImageView的内容
     * @return ViewHolder
     */
    public ViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId) {
        ImageView imageView = findView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置View的背景
     *
     * @param viewId     ImageView的id
     * @param drawableId Drawable
     * @return ViewHolder
     */
    public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId) {
        findView(viewId).setBackgroundResource(drawableId);
        return this;
    }

}
