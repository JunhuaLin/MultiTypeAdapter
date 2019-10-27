package cn.junhua.android.adapter.imp;

/**
 * OneToManyMatcher
 * Created by junhua.lin on 2017/12/28.
 */
public interface OneToManyMatcher<T> {

    void match(TypeMatcher<T> typeMatcher);
}
