package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.ItemViewBinder;

/**
 * mapping ItemViewBinder
 * Created by junhua.lin on 2017/12/28.
 */
public interface OneToManyMapper<T> {

    @SuppressWarnings("unchecked")
    OneToManyMatcher<T> mapping(ItemViewBinder<T, ?>... binders);

}
