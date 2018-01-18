package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.ViewBinder;

/**
 * TypeMatcher
 * Created by junhua.lin on 2017/12/28.
 */
public interface TypeMatcher<T> {
    Class<? extends ViewBinder<T>> onMatch(T bean, int position);
}
