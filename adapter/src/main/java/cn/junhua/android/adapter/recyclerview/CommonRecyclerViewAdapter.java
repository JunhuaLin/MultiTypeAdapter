package cn.junhua.android.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.junhua.android.adapter.exception.ViewBinderNotFoundException;

/**
 * 功能：Adapter封装简化使用步骤，适用于RecyclerView的任意类别条目的列表数据填充
 * created by 林军华 on 2016/5/18 0026.
 */
public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    // data res
    private List<Object> mList;
    private LayoutInflater mLayoutInflater;
    // save SingleTypeViewBinder
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
     * @param viewBinder SingleTypeViewBinder
     */
    public void registerViewBinder(ViewBinder viewBinder) {
        mViewBinderManager.add(viewBinder);
    }

    /**
     * 注销ViewBinder
     *
     * @param viewBinder SingleTypeViewBinder
     * @return SingleTypeViewBinder
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
        return mViewBinderManager.get(clazz).dispatchLayoutId(bean, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Object bean = mList.get(position);
        mViewBinderManager.get(bean.getClass()).dispatchBindView(holder, bean, position);
    }
}
