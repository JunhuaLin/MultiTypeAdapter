package cn.junhua.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.OneToManyMapper;

/**
 * a common adapter for RecyclerView on Android
 * created by linjunhua on 2016/5/18 0026.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = MultiTypeAdapter.class.getSimpleName();
    // data res
    private List<?> mList;
    private LayoutInflater mLayoutInflater;
    // save ViewBinder
    private Map<Class, ViewBinder> mViewBinderMap;
    // count view
    private int mViewSizeTemp;
    // default ViewBinder
    private ViewBinder mDefaultViewBinder;

    public MultiTypeAdapter() {
        mViewBinderMap = new HashMap<>(3);
        mList = Collections.emptyList();
    }

    public void setDefaultViewBinder(ViewBinder viewBinder) {
        if (viewBinder == null) return;
        viewBinder.setAdapter(this);
        mDefaultViewBinder = viewBinder;
    }

    public void register(ViewBinder viewBinder) {
        if (viewBinder == null) return;
        viewBinder.setAdapter(this);
        mViewBinderMap.put(viewBinder.getBeanClass(), viewBinder);
    }

    public <T> OneToManyMapper<T> register(Class<T> beanClass) {
        return new OneToManyBuilder<>(this, beanClass);
    }

    public void register(Collection<? extends ViewBinder> viewBinderCollection) {
        if (viewBinderCollection == null) return;
        for (ViewBinder vb : viewBinderCollection) {
            register(vb);
        }
    }

    public void unregister(ViewBinder viewBinder) {
        if (viewBinder == null) return;
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

    @SuppressWarnings("unchecked")
    @Override
    public int getItemViewType(int position) {
        Object bean = mList.get(position);
        Class beanClass = bean.getClass();
        ViewBinder viewBinder;
        if (mViewBinderMap.containsKey(beanClass)) {
            viewBinder = mViewBinderMap.get(beanClass);
        } else if (mDefaultViewBinder != null) {
            viewBinder = mDefaultViewBinder;
        } else {
            throw new ViewBinderNotFoundException(beanClass);
        }
        mViewSizeTemp = viewBinder.onCountView(bean, position);
        return viewBinder.onCreateItemView(bean, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new ViewHolder(
                mLayoutInflater.inflate(viewType, parent, false),
                parent, mViewSizeTemp);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object bean = mList.get(position);
        getCurrentViewBinder(position).onBindView(holder, bean, position);
    }

    @Override
    public long getItemId(int position) {
        return getCurrentViewBinder(position).getItemId(position);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        getCurrentViewBinder(holder.getAdapterPosition()).onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return getCurrentViewBinder(holder.getAdapterPosition()).onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        getCurrentViewBinder(holder.getAdapterPosition()).onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        getCurrentViewBinder(holder.getAdapterPosition()).onViewDetachedFromWindow(holder);
    }

    private ViewBinder getCurrentViewBinder(int position) {
        ViewBinder viewBinder = mViewBinderMap.get(mList.get(position).getClass());
        if (viewBinder == null) {
            viewBinder = mDefaultViewBinder;
        }
        return viewBinder;
    }
}
