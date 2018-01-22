package cn.junhua.android.adapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.junhua.android.adapter.imp.TypeMatcher;
import cn.junhua.android.adapter.imp.OneToManyMapper;
import cn.junhua.android.adapter.imp.OneToManyMatcher;

/**
 * MultiTypeViewBinder Builder
 * Created by junhua.lin on 2017/12/28.
 */
public class OneToManyBuilder<T> implements OneToManyMapper<T>, OneToManyMatcher<T> {

    private MultiTypeAdapter mMultiTypeAdapter;
    private Class<T> mBeanClass;
    private List<ViewBinder<T>> mViewBinderList;

    OneToManyBuilder(MultiTypeAdapter mMultiTypeAdapter, Class<T> mBeanClass) {
        this.mMultiTypeAdapter = mMultiTypeAdapter;
        this.mBeanClass = mBeanClass;
        mViewBinderList = Collections.emptyList();
    }

    @Override
    @SafeVarargs
    public final OneToManyMatcher<T> mapping(ViewBinder<T>... viewBinderList) {
        mViewBinderList = Arrays.asList(viewBinderList);
        return this;
    }

    @Override
    public MultiViewBinder<T> match(TypeMatcher<T> typeMatcher) {
        MultiViewBinder<T> viewBinder = new MultiViewBinder<>(mBeanClass, mViewBinderList, typeMatcher);
        mMultiTypeAdapter.register(viewBinder);
        return viewBinder;
    }
}
