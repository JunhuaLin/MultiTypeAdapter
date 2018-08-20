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
    private ViewBinder mViewBinderTemp;
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
        mViewBinderTemp = getCurrentViewBinder(position);
        mViewSizeTemp = mViewBinderTemp.onCountView(bean, position);
        return mViewBinderTemp.onCreateItemView(bean, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new ViewHolder(
                mLayoutInflater.inflate(viewType, parent, false),
                parent, mViewBinderTemp, mViewSizeTemp);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        Object bean = mList.get(position);
        holder.getViewBinder().onBindView(holder, bean, position, payloads);
    }

    @Override
    public long getItemId(int position) {
        return getCurrentViewBinder(position).getItemId(position);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.getViewBinder().onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return holder.getViewBinder().onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        holder.getViewBinder().onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.getViewBinder().onViewDetachedFromWindow(holder);
    }

    @SuppressWarnings("unchecked")
    private ViewBinder getCurrentViewBinder(int position) {
        Object bean = mList.get(position);
        Class beanClass = bean.getClass();
        ViewBinder viewBinder;
        if (mViewBinderMap.containsKey(beanClass)) {
            viewBinder = mViewBinderMap.get(beanClass);
            if (viewBinder instanceof MultiViewBinder) {
                MultiViewBinder multiViewBinder = (MultiViewBinder) viewBinder;
                viewBinder = multiViewBinder.getViewBinder(bean, position);
            }
        } else if (mDefaultViewBinder != null) {
            viewBinder = mDefaultViewBinder;
        } else {
            throw new ViewBinderNotFoundException(beanClass);
        }
        return viewBinder;
    }
}
