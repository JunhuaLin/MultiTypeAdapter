package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.binder.ViewBinder;

/**
 * 类型匹配器：处理一对多匹配条件
 * Created by junhua.lin on 2017/12/28.
 */
public interface TypeMatcher<T> {
    Class<? extends ViewBinder<T>> onMatch(T bean, int position);
}
