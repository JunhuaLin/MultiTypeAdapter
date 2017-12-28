package cn.junhua.android.adapter.binder;

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
    @SuppressWarnings("unchecked")
    public OneToManyMatcher<T> map(ViewBinder<T>... viewBinderList) {
        this.viewBinderList = Arrays.asList(viewBinderList);
        return this;
    }

    @Override
    public MultiTypeViewBinder<T> match(OnMatchViewBinder<T> onMatchViewBinder) {
        MultiTypeViewBinder<T> viewBinder = new MultiTypeViewBinder<>(mBeanClass, viewBinderList, onMatchViewBinder, viewBinderList.size());
        multiTypeAdapter.registerViewBinder(viewBinder);
        return viewBinder;
    }
}
