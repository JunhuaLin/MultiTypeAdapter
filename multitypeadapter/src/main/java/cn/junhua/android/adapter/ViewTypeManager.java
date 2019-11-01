package cn.junhua.android.adapter;

import java.util.ArrayList;

/**
 * ViewTypeManager
 * created by linjunhua on 2019年10月27日19:06:01
 */
class ViewTypeManager extends ArrayList<ViewType> {


    int indexByClass(Class<?> clazz) {
        Class<?> tempClazz;
        int len = size();
        for (int i = 0; i < len; i++) {
            tempClazz = get(i).clazz;
            if (tempClazz == clazz) {
                return i;
            }
        }

        for (int i = 0; i < len; i++) {
            tempClazz = get(i).clazz;
            if (tempClazz.isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }

}
