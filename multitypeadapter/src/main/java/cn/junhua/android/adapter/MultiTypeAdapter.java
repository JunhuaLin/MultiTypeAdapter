package cn.junhua.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collection;
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
 * a common adapter for RecyclerView on Android
 * created by linjunhua on 2016/5/18 0026.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    // data res
    private List<?> mList;
    private LayoutInflater mLayoutInflater;
    // save ViewBinder
    private Map<Class, ViewBinder> mViewBinderMap;
    // count view
    private int mViewSizeTemp;
    // current position
    private int mPosition;

    public MultiTypeAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewBinderMap = new HashMap<>(3);
        mList = Collections.emptyList();
    }

    public void register(ViewBinder viewBinder) {
        if (viewBinder == null) {
            return;
        }
        mViewBinderMap.put(viewBinder.getBeanClass(), viewBinder);
    }

    public <T> OneToManyMapper<T> register(Class<T> beanClass) {
        return new OneToManyBuilder<>(this, beanClass);
    }

    public void register(Collection<? extends ViewBinder> viewBinderCollection) {
        if (viewBinderCollection == null) {
            return;
        }
        for (ViewBinder vb : viewBinderCollection) {
            mViewBinderMap.put(vb.getBeanClass(), vb);
        }
    }


    public void unregister(ViewBinder viewBinder) {
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
        mPosition = position;
        return viewBinder.performCreateItemView(bean, position);
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

    @Override
    public long getItemId(int position) {
        Log.d("666", "getItemId " + mPosition);

        return getCurrentViewBinder().performGetItemId(mList.get(position));
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        Log.d("666", "onViewRecycled " + mPosition);

        getCurrentViewBinder().onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        Log.d("666", "onFailedToRecycleView " + mPosition);

        return getCurrentViewBinder().onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        Log.d("666", "onViewAttachedToWindow " + mPosition);

        getCurrentViewBinder().onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        Log.d("666", "onViewDetachedFromWindow " + mPosition);

        getCurrentViewBinder().onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d("666", "onAttachedToRecyclerView " + mPosition);

        getCurrentViewBinder().onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Log.d("666", "onDetachedFromRecyclerView " + mPosition);

        getCurrentViewBinder().onDetachedFromRecyclerView(recyclerView);
    }

    private ViewBinder getCurrentViewBinder() {
        Log.d("666", "getCurrentViewBinder " + mPosition);
        return mViewBinderMap.get(mList.get(mPosition).getClass());
    }
}
