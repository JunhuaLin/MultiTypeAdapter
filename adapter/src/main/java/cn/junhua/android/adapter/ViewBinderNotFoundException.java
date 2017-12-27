package cn.junhua.android.adapter;

import android.support.annotation.NonNull;

/**
 * Created by junhua.lin on 2017/12/21.
 */
public class ViewBinderNotFoundException extends RuntimeException {

    ViewBinderNotFoundException(@NonNull Class<?> clazz) {
        super("Do you have added the binder for {className}.class in the adapter?"
                .replace("{className}", clazz.getSimpleName()));
    }
}