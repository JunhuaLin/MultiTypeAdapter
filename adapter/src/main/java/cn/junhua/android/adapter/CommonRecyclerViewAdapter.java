package cn.junhua.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 功能：Adapter封装简化使用步骤，适用于RecyclerView的任意类别条目的列表数据填充<br/>
 * created by 林军华 on 2016/5/18 0026.<br/>
 */
public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<CommonRecyclerViewAdapter.ViewHolder> {
    // data res
    private List<Object> mList;
    private LayoutInflater mLayoutInflater;
    // save ViewBinder
    private ViewBinderManager mViewBinderManager;

    /**
     * 创建Adapter必须使用ViewBinder
     *
     * @param context
     * @param viewBinderList
     */
    public CommonRecyclerViewAdapter(Context context, List<ViewBinder> viewBinderList) {
        this(context, viewBinderList, null);
    }

    public CommonRecyclerViewAdapter(Context context, List<ViewBinder> viewBinderList, List<Object> dataList) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewBinderManager = new ViewBinderManager();
        mViewBinderManager.addAll(viewBinderList);
        setList(dataList);
    }


    /**
     * 得到内部数据集合
     *
     * @return
     */
    public List<Object> getList() {
        return mList;
    }

    /**
     * 设置或重置内部数据集合内容
     *
     * @param list
     */
    public void setList(List<Object> list) {
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
    public void append(List<Object> list) {
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
    public int getItemViewType(int position) {
        Object bean = mList.get(position);
        int layoutId = mViewBinderManager.get(bean.getClass()).getLayoutId();
        // 你必须创建一个ViewBinder实例与JavaBean对应。
        if (layoutId < 0) {
            throw new RuntimeException("You must create a " + ViewBinder.class.getCanonicalName() + " instance for "
                    + bean.getClass().getCanonicalName() + " .");
        }
        // 布局文件的id作为标识来创建ViewHolder
        return layoutId;
    }

    //当viewholder创建的时候回调
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(viewType, parent, false));
    }

    //当viewholder和数据绑定时回调
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Object bean = mList.get(position);// 要填充的数据
        ViewBinder viewBinder = mViewBinderManager.get(bean.getClass());
        // 你必须创建一个ViewBinder实例与JavaBean对应。
        if (viewBinder == null) {
            throw new RuntimeException("You must create a " + ViewBinder.class.getCanonicalName() + " instance for "
                    + bean.getClass().getCanonicalName() + " .");
        }

        viewBinder.bindView(holder, bean, position);
    }


    /**
     * 设置条目中控件显示信息的封装类
     */
    public static abstract class ViewBinder {
        // 布局文件并区分不同类别的条目
        private int mLayoutId;

        /**
         * 填充数据类类型此处仅仅作为不同布局的标识,用于根据数据源区分布局对象<br/>
         * 因为：Bean->Layout->View<br/>
         */
        private Class<?> mBeanClass;

        public ViewBinder(Class<?> beanClass, int layoutId) {
            this.mLayoutId = layoutId;
            this.mBeanClass = beanClass;
        }

        public int getLayoutId() {
            return mLayoutId;
        }

        public Class<?> getBeanClass() {
            return mBeanClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            ViewBinder that = (ViewBinder) o;

            if (mLayoutId != that.mLayoutId)
                return false;
            return !(mBeanClass != null ? !mBeanClass.getCanonicalName().equals(that.mBeanClass.getCanonicalName())
                    : that.mBeanClass != null);

        }

        @Override
        public int hashCode() {
            int result = mLayoutId;
            result = 31 * result + (mBeanClass != null ? mBeanClass.getCanonicalName().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ViewBinder{" +
                    "mLayoutId=" + mLayoutId +
                    ", mBeanClass=" + mBeanClass +
                    '}';
        }

        /**
         * 设置条目中控件显示的信息
         *
         * @param holder   封装adapter中item的复用操作代码的对象
         * @param bean     数据Bean需要强制类型转化
         * @param position 当前条目位置
         */
        public abstract void bindView(ViewHolder holder, Object bean, int position);
    }

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

    /**
     * ViewBinder的列表管理器
     *
     * @author lin
     *         <p/>
     *         2016年6月6日上午11:26:57
     */
    private static class ViewBinderManager extends HashMap<Class<?>, ViewBinder> {

        /**
         *
         */
        private static final long serialVersionUID = -7149248838995627451L;

        /**
         * 添加ViewBinder
         */
        public boolean add(ViewBinder viewBinder) {
            if (viewBinder == null) {
                return false;
            }
            //每类布局只能对应一种绑定数据的方式,重复添加会被覆盖
            return put(viewBinder.getBeanClass(), viewBinder) != null;
        }

        public boolean addAll(Collection<? extends ViewBinder> collection) {
            boolean b = true;
            for (ViewBinder vb : collection) {
                b &= add(vb);
            }
            return b;
        }

    }
}
