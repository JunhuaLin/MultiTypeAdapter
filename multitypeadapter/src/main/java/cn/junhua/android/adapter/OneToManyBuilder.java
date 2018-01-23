package cn.junhua.android.adapter;

import android.support.annotation.CheckResult;

import cn.junhua.android.adapter.imp.OneToManyMapper;
import cn.junhua.android.adapter.imp.OneToManyMatcher;
import cn.junhua.android.adapter.imp.TypeMatcher;

/**
 * MultiTypeViewBinder Builder
 * Created by junhua.lin on 2017/12/28.
 */
public class OneToManyBuilder<T> implements OneToManyMapper<T>, OneToManyMatcher<T> {

    private MultiTypeAdapter mMultiTypeAdapter;
    private Class<T> mBeanClass;
    private ViewBinder<T>[] mViewBinderList;

    OneToManyBuilder(MultiTypeAdapter mMultiTypeAdapter, Class<T> mBeanClass) {
        this.mMultiTypeAdapter = mMultiTypeAdapter;
        this.mBeanClass = mBeanClass;
    }

    @Override
    @CheckResult
    @SafeVarargs
    public final OneToManyMatcher<T> mapping(ViewBinder<T>... viewBinderList) {
        if (viewBinderList == null) {
            throw new NullPointerException();
        }
        mViewBinderList = viewBinderList;
        return this;
    }

    @Override
    public MultiViewBinder<T> match(TypeMatcher<T> typeMatcher) {
        MultiViewBinder<T> viewBinder = new MultiViewBinder<>(mBeanClass, mViewBinderList, typeMatcher);
        mMultiTypeAdapter.register(viewBinder);
        return viewBinder;
    }
}
