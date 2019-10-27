package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.ItemViewBinder;

/**
 * TypeMatcher
 * Created by junhua.lin on 2017/12/28.
 */
public interface TypeMatcher<T> {
    Class<? extends ItemViewBinder<T, ?>> onMatch(T bean, int position);
}
