package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.binder.MultiTypeViewBinder;

/**
 * Created by junhua.lin on 2017/12/28.
 */

public interface OneToManyMatcher<T> {

    MultiTypeViewBinder<T> match(OnMatchViewBinder<T> onMatchViewBinder);
}
