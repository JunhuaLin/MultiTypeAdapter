package cn.junhua.android.adapter.binder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.adapter.imp.TypeMatcher;
import cn.junhua.android.adapter.imp.OneToManyMapper;
import cn.junhua.android.adapter.imp.OneToManyMatcher;

/**
 * MultiTypeViewBinder构造器
 * Created by junhua.lin on 2017/12/28.
 */
public class OneToManyBuilder<T> implements OneToManyMapper<T>, OneToManyMatcher<T> {

    private MultiTypeAdapter mMultiTypeAdapter;
    private Class<T> mBeanClass;
    private List<ViewBinder<T>> mViewBinderList;

    public OneToManyBuilder(MultiTypeAdapter mMultiTypeAdapter, Class<T> mBeanClass) {
        this.mMultiTypeAdapter = mMultiTypeAdapter;
        this.mBeanClass = mBeanClass;
        mViewBinderList = Collections.emptyList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public OneToManyMatcher<T> mapping(ViewBinder<T>... viewBinderList) {
        mViewBinderList = Arrays.asList(viewBinderList);
        return this;
    }

    @Override
    public MultiTypeViewBinder<T> match(TypeMatcher<T> typeMatcher) {
        MultiTypeViewBinder<T> viewBinder = new MultiTypeViewBinder<>(mBeanClass, mViewBinderList, typeMatcher);
        mMultiTypeAdapter.registerViewBinder(viewBinder);
        return viewBinder;
    }
}
