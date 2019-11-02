package cn.junhua.android.adapter;


import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SimpleItemViewBinder用于快速创建条目，简化ItemViewBinder创建过程，提供通用CommonViewHolder
 * Created by junhua.lin on 2017/12/28.
 */
public abstract class SimpleItemViewBinder<T> extends ItemViewBinder<T, CommonViewHolder> {

    private int initialCapacity = 6;

    @Override
    public CommonViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View root = inflater.inflate(getLayoutId(), parent, false);
        initialCapacity = onInitialCapacity();
        return new CommonViewHolder(root, parent, initialCapacity);
    }

    /**
     * 当初始化CommonViewHolder时会调
     *
     * @return initialCapacity View缓存池的初始化大小
     */
    protected int onInitialCapacity() {
        return initialCapacity;
    }

    /**
     * 获取布局文件
     *
     * @return layout res id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 要在CommonViewHolder创建之前调用才能生效，即构造函数中调用
     *
     * @param initialCapacity View缓存池的初始大小
     */
    protected void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }
}
