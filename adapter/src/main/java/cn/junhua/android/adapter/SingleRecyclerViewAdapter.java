package cn.junhua.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 功能：Adapter封装简化使用步骤，适用于RecyclerView的单个条目类别条目的列表数据填充<br/>
 * created by 林军华 on 2016/5/18 0026.<br/>
 */
public abstract class SingleRecyclerViewAdapter<T> extends RecyclerView.Adapter<SingleRecyclerViewAdapter.ViewHolder> {
    // data res
    private List<T> mList;
    private LayoutInflater mLayoutInflater;
    protected int mLayoutId;


    /**
     * 初始化adapter
     *
     * @param context
     * @param layoutId
     */
    public SingleRecyclerViewAdapter(Context context, int layoutId) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mLayoutId = layoutId;
    }


    /**
     * 得到内部数据集合
     *
     * @return
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 设置或重置内部数据集合内容
     *
     * @param list
     */
    public void setList(List<T> list) {
        if (mList != null) {
            mList.clear();
        }
        mList = list;
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
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(this.mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // 要填充的数据
        T bean = mList.get(position);
        onBindView(holder, bean, position);
    }

    /**
     * 设置条目中控件显示的信息
     *
     * @param holder   封装adapter中item的复用操作代码的对象
     * @param bean     数据Bean需要强制类型转化
     * @param position 当前条目位置
     */
    public abstract void onBindView(ViewHolder holder, T bean, int position);


    /**
     * 通用ViewHolder MultiTypeBaseAdapter的内部类，封装adapter中item的复用操作代码<br/>
     * 使用方法：<br/>
     * 通过ViewHolder对象的方法getView(...)传入View的id得到对应控件<br/>
     */
    public final static class ViewHolder extends RecyclerView.ViewHolder {
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
         * @param textViewId TextView的id
         * @param text       TextView的内容
         * @return ViewHolder自己实现链式编程
         */
        public ViewHolder setText(int textViewId, String text) {
            TextView textView = getView(textViewId);
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

}
