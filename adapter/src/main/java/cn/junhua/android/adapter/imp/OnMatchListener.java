package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.binder.ViewBinder;

/**
 * 处理一对多匹配条件
 * Created by junhua.lin on 2017/12/28.
 */
public interface OnMatchListener<T> {
    Class<? extends ViewBinder<T>> onMatch(T bean, int position);
}
