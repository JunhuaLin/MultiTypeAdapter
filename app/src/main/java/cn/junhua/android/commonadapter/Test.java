package cn.junhua.android.commonadapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junhua on 17-3-15.
 */

public class Test {

    public static final void main(String[] args) {
        Map<Class<?>, String> map = new HashMap<>();
        map.put(Integer.class, "Integer");
        System.out.println(map.get(Integer.class));
        map.put(Integer.class, "Integer1");
        map.put(String.class, null);
        System.out.println(map.get(Integer.class));
        System.out.println(map.get(String.class));
    }
}
