package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.ViewBinder;

/**
 * mapping ViewBinder
 * Created by junhua.lin on 2017/12/28.
 */
public interface OneToManyMapper<T> {

    @SuppressWarnings("unchecked")
    OneToManyMatcher<T> mapping(ViewBinder<T>... viewBinderList);

}
