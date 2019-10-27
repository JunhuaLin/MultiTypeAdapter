package cn.junhua.android.adapter;


import cn.junhua.android.adapter.imp.Matcher;

/**
 * ViewType
 * created by linjunhua on 2019年10月27日19:06:01
 */
class ViewType {
    final Class<?> clazz;
    final ItemViewBinder binder;
    final Matcher<?> matcher;

    ViewType(Class<?> clazz, ItemViewBinder binder) {
        this(clazz, binder, new Matcher<Object>() {
            @Override
            public int onMatch(Object bean, int position) {
                return 0;
            }
        });
    }

    ViewType(Class<?> clazz, ItemViewBinder binder, Matcher<?> matcher) {
        this.clazz = clazz;
        this.binder = binder;
        this.matcher = matcher;
    }
}
