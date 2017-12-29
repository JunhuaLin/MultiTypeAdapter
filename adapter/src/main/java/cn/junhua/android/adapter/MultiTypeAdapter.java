package cn.junhua.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.binder.OneToManyBuilder;
import cn.junhua.android.adapter.binder.ViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.OneToManyMapper;

/**
 * 功能：Adapter封装简化使用步骤，适用于RecyclerView的任意类别条目的列表数据填充
 * created by 林军华 on 2016/5/18 0026.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    // data res
    private List<?> mList;
    private LayoutInflater mLayoutInflater;
    // save ViewBinder
    private Map<Class, ViewBinder> mViewBinderMap;
    // count view
    private int mViewSizeTemp;

    public MultiTypeAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewBinderMap = new HashMap<>(3);
        mList = Collections.emptyList();
    }

    /**
     * 注册ViewBinder
     *
     * @param viewBinder SingleTypeViewBinder
     */
    public void registerViewBinder(ViewBinder viewBinder) {
        if (viewBinder == null) {
            return;
        }
        mViewBinderMap.put(viewBinder.getBeanClass(), viewBinder);
    }

    /**
     * @param beanClass 数据对象的类类型
     * @param <T>       数据的类型
     * @return OneToManyMapper<T>
     */
    public <T> OneToManyMapper<T> registerViewBinder(Class<T> beanClass) {
        return new OneToManyBuilder<>(this, beanClass);
    }

    /**
     * 注销ViewBinder
     */
    public void unregisterViewBinder(ViewBinder viewBinder) {
        if (viewBinder == null) {
            return;
        }
        mViewBinderMap.remove(viewBinder.getBeanClass());
    }

    public List<?> getList() {
        return mList;
    }

    public void setList(List<?> list) {
        mList.clear();
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object bean = mList.get(position);
        Class beanClass = bean.getClass();
        if (!mViewBinderMap.containsKey(beanClass)) {
            throw new ViewBinderNotFoundException(beanClass);
        }
        ViewBinder viewBinder = mViewBinderMap.get(beanClass);
        mViewSizeTemp = viewBinder.performCountView(bean, position);
        return viewBinder.performItemViewType(bean, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(viewType, parent, false), mViewSizeTemp);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object bean = mList.get(position);
        mViewBinderMap.get(bean.getClass()).performBindView(holder, bean, position);
    }
}
