package cn.junhua.android.adapter.imp;

/**
 * created by linjunhua on 2019/10/27
 */
public interface Matcher<T> {
    int onMatch(T bean, int position);
}