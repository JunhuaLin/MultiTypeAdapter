package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.bean.taobao.MenuBean;

/**
 * 淘宝菜单
 * Created by junhua.lin on 2017/12/29.
 */
public class MenuViewHinder extends SingleTypeViewBinder<MenuBean> {
    public MenuViewHinder(Class<MenuBean> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public int onCountView(MenuBean bean, int position) {
        return 0;
    }

    @Override
    public void onBindView(ViewHolder holder, MenuBean bean, int position) {

    }
}
