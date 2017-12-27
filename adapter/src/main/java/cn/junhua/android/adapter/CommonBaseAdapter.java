package cn.junhua.android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 功能：Adapter封装简化使用步骤，适用于ListView任意类别条目的列表数据填充
 * <p>
 * created by 林军华 on 2016/5/18 0026.
 */
public final class CommonBaseAdapter extends BaseAdapter {
    // Log TAG
    private static final String TAG = CommonBaseAdapter.class.getSimpleName();
    // data res
    private List<Object> mList;
    private LayoutInflater mLayoutInflater;
    // save ViewBinder
    private ViewBinderListManager mViewBinderListManager;

    public CommonBaseAdapter(Context context, List<ViewBinder> viewBinders) {
        this(context, viewBinders, null);
    }

    public CommonBaseAdapter(Context context, List<ViewBinder> viewBinders, List<Object> list) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewBinderListManager = new ViewBinderListManager();
        mViewBinderListManager.addAll(viewBinders);
        setList(list);
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

    public void append(List<Object> list) {
        if (mList != null) {
            mList.addAll(list);
        }
    }

    /**
     * 该返回值必须小于getViewTypeCount的值，并且不同类型返回值要从0依次递增1(因为要做数组下标...)<br/>
     * 并作为内部不同条目缓存索引
     */
    @Override
    public int getItemViewType(int position) {
        Object bean = getItem(position);
        int index = mViewBinderListManager.indexOf(bean);
        if (index < 0) {
            throw new ViewBinderNotFoundException(bean.getClass());
        }
        return index;
    }

    @Override
    public int getViewTypeCount() {
        return mViewBinderListManager.size();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewTag")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object bean = getItem(position);
        ViewBinder viewBinder = mViewBinderListManager.get(bean.getClass());
        if (viewBinder == null) {
            throw new ViewBinderNotFoundException(bean.getClass());
        }

        int layoutId = viewBinder.getLayoutId();
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(layoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(layoutId, holder);
        } else {
            holder = (ViewHolder) convertView.getTag(layoutId);
        }

        viewBinder.wrapperBindView(holder, bean, position);
        return convertView;
    }

    /**
     * 设置条目中控件显示信息的封装类
     */
    public static abstract class ViewBinder<T> {
        // 布局文件
        private int mLayoutId;
        // 区分不同类别的条目
        private int mTypeId;

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

        public int getViewType() {
            return mTypeId;
        }

        private void setTypeId(int mTypeId) {
            this.mTypeId = mTypeId;
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
            return "ViewBinder{" + "mLayoutId=" + mLayoutId + ", mTypeId=" + mTypeId + ", mBeanClass="
                    + mBeanClass.getCanonicalName() + '}';
        }

        /**
         * 内部进行数据类型强制转化
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
    public final static class ViewHolder {
        private SparseArray<View> mViews;
        private View convertView;

        /**
         * 传入复用对象
         *
         * @param root
         */
        public ViewHolder(View root) {
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
     *         <p>
     *         2016年6月6日上午11:26:57
     */
    private static class ViewBinderListManager extends ArrayList<ViewBinder> {

        /**
         *
         */
        private static final long serialVersionUID = -7149248838995627451L;

        /**
         * 添加ViewBinder并自动生成ViewBinder的TypeId
         */
        @Override
        public boolean add(ViewBinder viewBinder) {
            if (viewBinder == null) {
                return false;
            }
            ViewBinder temp = get(viewBinder.getBeanClass());

            if (temp == null) {// 首次添加
                viewBinder.setTypeId(size());
                super.add(viewBinder);
            } else {// 替换
                // 替换原来的TypeId
                viewBinder.setTypeId(temp.getViewType());
                super.remove(temp);
                super.add(viewBinder);
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends ViewBinder> collection) {
            boolean b = false;
            for (ViewBinder vb : collection) {
                b |= add(vb);
            }
            return b;
        }

        /**
         * 通过JavaBean的Class得到对应的ViewBinder
         *
         * @param clazz
         * @return
         */
        public ViewBinder get(Class<?> clazz) {
            if (clazz == null) {
                return null;
            }

            String modelCanonicalName = clazz.getCanonicalName();
            for (ViewBinder viewBinder : this) {
                if (viewBinder.getBeanClass().getCanonicalName().equals(modelCanonicalName)) {
                    return viewBinder;
                }
            }
            return null;
        }

        /**
         * 通过数据源JavaBean的实例得到ViewBinder在复用列表中的索引位置
         */
        @Override
        public int indexOf(Object object) {
            ViewBinder viewBinder = get(object.getClass());
            if (viewBinder == null)
                return -1;
            return viewBinder.getViewType();
        }

    }
}
