package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class ServerViewHinder extends SingleTypeViewBinder<BannerBean> {


    public ServerViewHinder(Class<BannerBean> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public int onCountView(BannerBean bean, int position) {
        return 0;
    }

    @Override
    public void onBindView(ViewHolder holder, BannerBean bean, int position) {

    }
}
