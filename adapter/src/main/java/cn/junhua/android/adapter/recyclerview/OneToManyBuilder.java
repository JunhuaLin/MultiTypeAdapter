package cn.junhua.android.adapter.recyclerview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.adapter.imp.OnMatchViewBinder;
import cn.junhua.android.adapter.imp.OneToManyMapper;
import cn.junhua.android.adapter.imp.OneToManyMatcher;

/**
 * MultiTypeViewBinder构造器
 * Created by junhua.lin on 2017/12/28.
 */

public class OneToManyBuilder<T> implements OneToManyMapper<T>, OneToManyMatcher<T> {

    private MultiTypeAdapter multiTypeAdapter;
    private Class<T> mBeanClass;
    private List<ViewBinder<T>> viewBinderList;

    public OneToManyBuilder(MultiTypeAdapter multiTypeAdapter, Class<T> mBeanClass) {
        this.multiTypeAdapter = multiTypeAdapter;
        this.mBeanClass = mBeanClass;
        viewBinderList = new ArrayList<>();
    }

    @Override
    public OneToManyMatcher<T> map(ViewBinder<T>... viewBinderList) {
        this.viewBinderList = Arrays.asList(viewBinderList);
        return this;
    }

    @Override
    public MultiTypeViewBinder<T> match(final OnMatchViewBinder<T> onMatchViewBinder) {
        MultiTypeViewBinder<T> viewBinder = new MultiTypeViewBinder<T>(mBeanClass, viewBinderList) {
            @Override
            public Class<? extends ViewBinder> onMatch(Object bean, int position) {
                return onMatchViewBinder.onMatch((T) bean, position);
            }

            @Override
            public int initialCapacity() {
                return viewBinderList.size();
            }
        };
        multiTypeAdapter.registerViewBinder(viewBinder);
        return viewBinder;
    }
}
