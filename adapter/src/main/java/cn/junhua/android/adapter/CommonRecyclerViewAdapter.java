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
 * 功能：Adapter封装简化使用步骤，适用于RecyclerView的任意类别条目的列表数据填充
 * created by 林军华 on 2016/5/18 0026.
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
     * @param context Context
     */
    public CommonRecyclerViewAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewBinderManager = new ViewBinderManager();
    }

    /**
     * 注册ViewBinder
     *
     * @param viewBinder ViewBinder
     */
    public void registerViewBinder(ViewBinder viewBinder) {
        mViewBinderManager.add(viewBinder);
    }

    /**
     * 注销ViewBinder
     *
     * @param viewBinder ViewBinder
     * @return ViewBinder
     */
    public ViewBinder unregisterViewBinder(ViewBinder viewBinder) {
        return mViewBinderManager.remove(viewBinder);
    }

    public List<Object> getList() {
        return mList;
    }

    public void setList(List<Object> list) {
        if (mList != null) {
            mList.clear();
        }

        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 此处利用布局ID唯一性区分不同条目
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Object bean = mList.get(position);
        Class clazz = bean.getClass();
        if (!mViewBinderManager.containsKey(clazz)) {
            throw new ViewBinderNotFoundException(clazz);
        }
        return mViewBinderManager.get(clazz).getLayoutId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Object bean = mList.get(position);
        mViewBinderManager.get(bean.getClass()).wrapperBindView(holder, bean, position);
    }


    /**
     * 设置条目中控件显示信息的封装类
     */
    public static abstract class ViewBinder<T> {
        private int mLayoutId;

        /**
         * 填充数据类类型此处仅仅作为不同布局的标识,用于根据数据源区分布局对象
         * 因为：Bean->Layout->View
         */
        private Class<T> mBeanClass;

        public ViewBinder(Class<T> beanClass, int layoutId) {
            this.mLayoutId = layoutId;
            this.mBeanClass = beanClass;
        }

        public int getLayoutId() {
            return mLayoutId;
        }

        public Class<T> getBeanClass() {
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
         * 内部进行数据强制类型转换
         *
         * @param holder
         * @param bean
         * @param position
         */
        private void wrapperBindView(ViewHolder holder, Object bean, int position) {
            onBindView(holder, (T) bean, position);
        }

        /**
         * 设置条目中控件显示的信息
         *
         * @param holder   封装adapter中item的复用操作代码的对象
         * @param bean     数据Bean需要强制类型转化
         * @param position 当前条目位置
         */
        public abstract void onBindView(ViewHolder holder, T bean, int position);
    }

    /**
     * 通用ViewHolder MultiTypeBaseAdapter的内部类，封装adapter中item的复用操作代码
     * 使用方法：
     * 通过ViewHolder对象的方法getView(...)传入View的id得到对应控件
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

    /**
     * ViewBinder的列表管理器
     *
     * @author lin
     *         <p/>
     *         2016年6月6日上午11:26:57
     */
    private static class ViewBinderManager extends HashMap<Class, ViewBinder> {

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

        public ViewBinder remove(ViewBinder viewBinder) {
            return super.remove(viewBinder.getBeanClass());
        }
    }
}
