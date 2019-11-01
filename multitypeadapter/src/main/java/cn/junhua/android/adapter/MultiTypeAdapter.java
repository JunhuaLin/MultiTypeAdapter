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
    private static final String TAG = MultiTypeAdapter.class.getSimpleName();
    // data res
    private List<?> mList;
    private LayoutInflater mLayoutInflater;

    // save ItemViewBinder
    private ViewTypeManager mViewTypeManager;
    // default ItemViewBinder
    private ItemViewBinder mDefaultItemViewBinder;

    public MultiTypeAdapter() {
        mViewTypeManager = new ViewTypeManager();
        mList = Collections.emptyList();
    }

    /**
     * 设置默认的ItemViewBinder<br/>
     * <p>
     * 当没有默认ItemViewBinder时，如果数据的class没有匹配到对应的ItemViewBinder时候，会抛出异常提示没有匹配成功。<br/>
     * </p>
	 * <p>
     * 当设置了默认ItemViewBinder时，如果数据的class没有匹配到对应的ItemViewBinder时候，不会抛出异常而是使用该ItemViewBinder处理了。<br/>
     * </p>
     * @param binder {@link ItemViewBinder}
     */
    public void registerDefault(ItemViewBinder<Object, ?> binder) {
        if (binder == null) return;
        binder.adapter = this;
        mDefaultItemViewBinder = binder;
    }

    public <T> void register(Class<T> clazz, ItemViewBinder<T, ?> binder) {
        if (binder == null) return;
        register(new ViewType(clazz, binder));
    }

    void register(ViewType viewType) {
        if (viewType == null) return;
        viewType.binder.adapter = this;
        mViewTypeManager.add(viewType);
    }

    public <T> OneToManyMapper<T> register(Class<T> beanClass) {
        return new OneToManyBuilder<>(this, beanClass);
    }

    public List<?> getList() {
        return mList;
    }

    public void setList(List<?> list) {
		mList.clear();
		if (list == null){
			mList = Collections.emptyList();
		}else{
			mList = list;
		}
    }

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

    private ItemViewBinder getBinderByViewType(int itemViewType) {
        if (itemViewType == Integer.MAX_VALUE) {
            return mDefaultItemViewBinder;
        }

        return mViewTypeManager.get(itemViewType).binder;
    }

}
