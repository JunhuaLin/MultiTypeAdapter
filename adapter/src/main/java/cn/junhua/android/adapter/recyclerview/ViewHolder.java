package cn.junhua.android.adapter.recyclerview;

/**
 * Created by junhua.lin on 2017/12/27.
 */

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用ViewHolder MultiTypeBaseAdapter的内部类，封装adapter中item的复用操作代码
 * 使用方法：
 * 通过ViewHolder对象的方法getView(...)传入View的id得到对应控件
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View convertView;

    /**
     * 传入复用对象
     *
     * @param root
     */
    public ViewHolder(View root) {
        super(root);
        this.mViews = new SparseArray<View>();
        this.convertView = root;
    }

    /**
     * 得到对应Id的布局控件
     *
     * @param viewId 控件Id
     * @return 控件T extends View
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 便捷设置TextView的内容
     *
     * @param viewId TextView的id
     * @param text   TextView的内容
     * @return ViewHolder自己实现链式编程
     */
    public ViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 便捷设置ImageView的内容
     *
     * @param imageViewId ImageView的id
     * @param drawableId  ImageView的内容
     * @return ViewHolder自己实现链式编程
     */
    public ViewHolder setImage(int imageViewId, int drawableId) {
        ImageView imageView = getView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

}
