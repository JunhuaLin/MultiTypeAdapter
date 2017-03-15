package cn.junhua.android.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 功能：Adapter封装简化使用步骤，适用于ListView单条目类别条目的列表数据填充。
 * 使用：只需要继承实现onConvert方法即可。
 * created by 林军华 on 2016/5/18 0026.
 */
public abstract class SingleBaseAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    protected Context mContext;
    protected int mLayoutId;

    public SingleBaseAdapter(Context mContext, int layoutId) {
        this.mContext = mContext;
        this.mLayoutId = layoutId;
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        if (this.mList != null) {
            this.mList.clear();
        }
        this.mList = list;
    }


    /**
     * 向内部数据集合追加数据
     *
     * @param list
     */
    public void append(List<T> list) {
        if (mList == null) {
            mList = list;
        } else {
            mList.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(mContext, parent, mLayoutId);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        T bean = mList.get(position);
        onBindView(holder, bean, position);
        return holder.getConvertView();
    }

    /**
     * 设置条目中控件显示的信息
     *
     * @param holder   封装adapter中item的复用操作代码的对象
     * @param bean     数据Bean
     * @param position 当前条目位置
     */
    public abstract void onBindView(ViewHolder holder, T bean, int position);

    /**
     * ConnomAdapter的内部类，封装adapter中item的复用操作代码<br>
     * 使用方法：通过ViewHolder对象的方法getView(...)传入View的id得到对应控件
     */
    public static class ViewHolder {
        private SparseArray<View> mViews;
        private View convertView;

        public ViewHolder(Context context, ViewGroup parent, int layoutResId) {
            mViews = new SparseArray<View>();
            convertView = LayoutInflater.from(context).inflate(layoutResId, parent, false);
            convertView.setTag(this);
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
         * 得到条目复用对象
         *
         * @return convertView
         */
        public View getConvertView() {
            return convertView;
        }

        /**
         * 设置TextView的内容
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
         * 便捷设置TextView的内容
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

}
