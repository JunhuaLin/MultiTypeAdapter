package cn.junhua.android.adapter.imp;

import cn.junhua.android.adapter.binder.MultiViewBinder;

/**
 * OneToManyMatcher
 * Created by junhua.lin on 2017/12/28.
 */

public interface OneToManyMatcher<T> {

    MultiViewBinder<T> match(TypeMatcher<T> typeMatcher);
}
