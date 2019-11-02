package cn.junhua.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;
import cn.junhua.android.adapter.imp.Matcher;
import cn.junhua.android.adapter.imp.OneToManyMapper;

/**
 * a common adapter for RecyclerView on Android
 * created by linjunhua on 2016/5/18 0026.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region filed

    // data res
    private List<?> mList;
    private LayoutInflater mLayoutInflater;

    // save ItemViewBinder
    private ViewTypeManager mViewTypeManager;
    // default ItemViewBinder
    private ItemViewBinder mDefaultItemViewBinder;

    //endregion

    //region constructor
    public MultiTypeAdapter() {
        mViewTypeManager = new ViewTypeManager();
        mList = Collections.emptyList();
    }

    //endregion

    //region api

    /**
     * 设置默认的ItemViewBinder<br/>
     * <p>
     * 当没有默认ItemViewBinder时，如果数据的class没有匹配到对应的ItemViewBinder时候，会抛出异常提示没有匹配成功。<br/>
     * </p>
     * <p>
     * 当设置了默认ItemViewBinder时，如果数据的class没有匹配到对应的ItemViewBinder时候，不会抛出异常而是使用该ItemViewBinder处理。<br/>
     * </p>
     *
     * @param binder {@link ItemViewBinder}
     */
    public void setDefaultViewBinder(ItemViewBinder<Object, ?> binder) {
        if (binder == null) return;
        binder.adapter = this;
        mDefaultItemViewBinder = binder;
    }

    /**
     * 为不同数据条目注册不同的ItemViewBinder
     *
     * @param beanClass 数据类的class
     * @param binder    ItemViewBinder
     * @param <T>       数据结构泛型
     */
    public <T> void register(Class<T> beanClass, ItemViewBinder<T, ?> binder) {
        if (binder == null) return;
        register(new ViewType(beanClass, binder));
    }

    /**
     * 为同一个数据类型注册不同的ItemViewBinder，处理一种数据类型对应多种ItemViewBinder情况
     *
     * @param beanClass 数据类的class
     * @param <T>       数据结构泛型
     */
    public <T> OneToManyMapper<T> register(Class<T> beanClass) {
        return new OneToManyBuilder<>(this, beanClass);
    }

    public List<?> getList() {
        return mList;
    }

    /**
     * 设置展示的数据集合，内部仅引用数据集合
     *
     * @param list {@link List}
     */
    public void setList(List<?> list) {
        mList.clear();
        if (list == null) {
            mList = Collections.emptyList();
        } else {
            mList = list;
        }
    }

    //endregion

    //region recycler view api
    @Override
    public final int getItemCount() {
        return mList.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final int getItemViewType(int position) {
        Object data = mList.get(position);
        int index = mViewTypeManager.indexByClass(data.getClass());
        int subIndex = 0;
        if (index != -1) {
            ViewType viewType = mViewTypeManager.get(index);
            Matcher<Object> matcher = (Matcher<Object>) viewType.matcher;
            subIndex = matcher.onMatch(data, position);
        }

        if (index != -1 && subIndex != -1) {
            return index + subIndex;
        }

        if (mDefaultItemViewBinder != null) {
            return Integer.MAX_VALUE;
        }

        throw new ViewBinderNotFoundException(data.getClass());
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        return getBinderByViewType(viewType).onCreateViewHolder(mLayoutInflater, parent);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        int viewType = getItemViewType(position);
        Object data = mList.get(position);
        getBinderByViewType(viewType).onBindViewHolder(holder, data, position, payloads);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final long getItemId(int position) {
        int viewType = getItemViewType(position);
        Object data = mList.get(position);
        return getBinderByViewType(viewType).getItemId(data, position);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onViewRecycled(RecyclerView.ViewHolder holder) {
        getBinderByViewType(holder.getItemViewType()).onViewRecycled(holder);
    }


    @SuppressWarnings("unchecked")
    @Override
    public final boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return getBinderByViewType(holder.getItemViewType()).onFailedToRecycleView(holder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        getBinderByViewType(holder.getItemViewType()).onViewAttachedToWindow(holder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        getBinderByViewType(holder.getItemViewType()).onViewDetachedFromWindow(holder);
    }

    //endregion RecyclerView api

    //region private api

    /**
     * ItemViewBinder的注册信息
     *
     * @param viewType {@link ViewType}
     */
    void register(ViewType viewType) {
        if (viewType == null) return;
        viewType.binder.adapter = this;
        mViewTypeManager.add(viewType);
    }

    /**
     * 获取ViewType对应的ItemViewBinder
     *
     * @param itemViewType getItemViewType的返回结果
     * @return {@link ItemViewBinder}
     * @see #getItemViewType(int position)
     */
    private ItemViewBinder getBinderByViewType(int itemViewType) {
        if (itemViewType == Integer.MAX_VALUE) {
            return mDefaultItemViewBinder;
        }

        return mViewTypeManager.get(itemViewType).binder;
    }
    //endregion
}
