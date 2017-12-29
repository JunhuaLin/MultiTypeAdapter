package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.bean.taobao.ZhiBoBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class ZhiBoViewHinder extends SingleTypeViewBinder<ZhiBoBean> {


    public ZhiBoViewHinder(Class<ZhiBoBean> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public int onCountView(ZhiBoBean bean, int position) {
        return 0;
    }

    @Override
    public void onBindView(ViewHolder holder, ZhiBoBean bean, int position) {

    }
}
