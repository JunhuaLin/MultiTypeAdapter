package cn.junhua.android.adapter.exception;

import android.support.annotation.NonNull;

/**
 * RuntimeException
 * Created by junhua.lin on 2017/12/21.
 */
public class ViewBinderNotFoundException extends RuntimeException {

    public ViewBinderNotFoundException(@NonNull Class<?> clazz) {
        super(String.format("Do you have registered the binder for %s.class in the adapter?", clazz.getSimpleName()));
    }

    public ViewBinderNotFoundException(@NonNull Class<?> beanClass, @NonNull Class<?> viewBinderClass) {
        super(String.format("Do you have registered the %s.class for %s.class in the adapter?", viewBinderClass.getSimpleName(), beanClass.getSimpleName()));
    }
}
