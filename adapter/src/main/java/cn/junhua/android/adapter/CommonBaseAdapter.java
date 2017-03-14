package cn.junhua.android.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    protected Context mContext;
    protected int mLayoutId;

    public CommonBaseAdapter(Context mContext, List<T> list, int layoutId) {
        this.mList = list;
        this.mContext = mContext;
        this.mLayoutId = layoutId;
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
        onConvert(holder, bean, position);
        return holder.getConvertView();
    }

    /**
     * 设置条目中控件显示的信息
     *
     * @param holder   封装adapter中item的复用操作代码的对象
     * @param bean     数据Bean
     * @param position 当前条目位置
     */
    public abstract void onConvert(ViewHolder holder, T bean, int position);

    /**
     * ConnomAdapter的内部类，封装adapter中item的复用操作代码<br>
     * 使用方法：<br>
     * 1、通过ViewHolder.getInstance(...)方法传入必要的参数得到ViewHolder对象<br>
     * ，该过程已被封装到ConnomAdapter的内部<br>
     * 2、通过ViewHolder对象的方法getView(...)传入View的id得到对应控件<br>
     * 3、通过ViewHolder对象的方法getConvertView()得到条目复用对象convertView<br>
     * ，该过程已被封装到ConnomAdapter的内部<br>
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

    }

}
