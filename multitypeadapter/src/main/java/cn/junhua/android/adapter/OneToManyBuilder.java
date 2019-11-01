package cn.junhua.android.adapter;

import cn.junhua.android.adapter.imp.Matcher;
import cn.junhua.android.adapter.imp.OneToManyMapper;
import cn.junhua.android.adapter.imp.OneToManyMatcher;
import cn.junhua.android.adapter.imp.TypeMatcher;

/**
 * MultiTypeViewBinder Builder
 * Created by junhua.lin on 2017/12/28.
 */
public class OneToManyBuilder<T> implements OneToManyMapper<T>, OneToManyMatcher<T> {

    private MultiTypeAdapter adapter;
    private Class<T> mBeanClass;
    private ItemViewBinder<T, ?>[] binders;

    OneToManyBuilder(MultiTypeAdapter mMultiTypeAdapter, Class<T> mBeanClass) {
        this.adapter = mMultiTypeAdapter;
        this.mBeanClass = mBeanClass;
    }


    @Override
    public OneToManyMatcher<T> mapping(ItemViewBinder<T, ?>... binders) {
        this.binders = binders;
        return this;
    }


    @Override
    public void match(final TypeMatcher<T> typeMatcher) {
        if (binders == null || typeMatcher == null) return;

        for (ItemViewBinder<T, ?> binder : binders) {
            adapter.register(new ViewType(mBeanClass, binder, new Matcher<T>() {
                @Override
                public int onMatch(T bean, int position) {
                    Class<? extends ItemViewBinder<T, ?>> binderClazz = typeMatcher.onMatch(bean, position);
                    for (int i = 0; i < binders.length; i++) {
                        if (binders[i].getClass() == binderClazz) {
                            return i;
                        }
                    }
                    return -1;
                }
            }));
        }
    }
}
