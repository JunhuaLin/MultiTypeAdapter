package cn.junhua.android.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
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

    //region filed
    private SparseArray<View> mViewPool;
    private RecyclerView mRecyclerView;
    /**
     * 内部View缓存池的默认大小
     */
    private int mInitialCapacity;
    //endregion

    //region constructor

    /**
     * 默认创建，内部View缓存池大小默认为6
     *
     * @param itemView 布局文件
     * @param parent   父布局
     */
    public CommonViewHolder(View itemView, ViewGroup parent) {
        this(itemView, parent, 6);
    }

    /**
     * 默认创建
     *
     * @param itemView        布局文件
     * @param parent          父布局
     * @param initialCapacity 内部View缓存池大小
     */
    public CommonViewHolder(View itemView, ViewGroup parent, int initialCapacity) {
        super(itemView);
        //避免首次使用就触发SparseArray扩容
        this.mInitialCapacity = initialCapacity <= 4 ? 8 : initialCapacity;
        this.mRecyclerView = (RecyclerView) parent;
    }
    //endregion

    //region view api

    /**
     * findViewById同样的功能，内部提供缓存view的功能<br/>
     * <p>
     * findView只会执行一次findViewById，之后将View缓存在内部的SparseArray中，再次findView直接从缓存中获取。
     *
     * @param viewId viewId
     * @param <T>    View的泛型
     * @return 具体的View
     */
    public <T extends View> T findView(@IdRes int viewId) {
        //延迟初始化
        if (mViewPool == null) {
            mViewPool = new SparseArray<>(mInitialCapacity);
        }

        View view = mViewPool.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViewPool.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取父RecyclerView
     *
     * @return {@link RecyclerView}
     */
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 快捷设置TextView的文本
     *
     * @param viewId view id
     * @param text   string
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setText(@IdRes int viewId, String text) {
        TextView textView = findView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 快捷设置TextView的文本
     *
     * @param viewId view id
     * @param resId  string res id
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = findView(viewId);
        textView.setText(resId);
        return this;
    }

    /**
     * 快捷设置TextView的文本颜色
     *
     * @param viewId         view id
     * @param colorStateList {@link ColorStateList}
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setTextColor(@IdRes int viewId, ColorStateList colorStateList) {
        TextView textView = findView(viewId);
        textView.setTextColor(colorStateList);
        return this;
    }

    /**
     * 快捷设置TextView的文本颜色
     *
     * @param viewId   view id
     * @param colorInt 颜色值
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setTextColor(@IdRes int viewId, @ColorInt int colorInt) {
        TextView textView = findView(viewId);
        textView.setTextColor(colorInt);
        return this;
    }

    /**
     * 快捷设置ImageView的src
     *
     * @param imageViewId view id
     * @param drawableId  res id
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setImageResource(@IdRes int imageViewId, @DrawableRes int drawableId) {
        ImageView imageView = findView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    /**
     * 快捷设置View的背景
     *
     * @param viewId     view id
     * @param drawableId res id
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int drawableId) {
        findView(viewId).setBackgroundResource(drawableId);
        return this;
    }

    /**
     * 设置条目的根itemView的点击事件
     *
     * @param onClickListener {@link View.OnClickListener}
     * @return {@link CommonViewHolder}
     */
    public CommonViewHolder setOnItemClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 清除View缓存，一般不需要调用
     */
    public void clear() {
        if (mViewPool != null) {
            mViewPool.clear();
        }
    }
    //endregion

    //region  resources api
    public Context getContext() {
        return itemView.getContext();
    }

    public Resources getResources() {
        return itemView.getResources();
    }

    /**
     * Return the string value associated with a particular resource ID.  It
     * will be stripped of any styled text information.
     *
     * @param id The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     * @return String The string data associated with the resource,
     * stripped of styled text information.
     */
    @NonNull
    public String getString(@StringRes int id) {
        return getResources().getString(id);
    }
    //endregion
}
