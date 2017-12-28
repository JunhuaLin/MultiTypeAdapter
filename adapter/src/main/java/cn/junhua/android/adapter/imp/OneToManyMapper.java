package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.recyclerview.ViewBinder;

/**
 * Created by junhua.lin on 2017/12/28.
 */

public interface OneToManyMapper<T> {

    OneToManyMatcher<T> map(ViewBinder<T>... viewBinderList);

}
