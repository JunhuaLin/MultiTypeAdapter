package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;
import cn.junhua.android.commonadapter.bean.taobao.GoodsShowBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class GoodsShowViewHinder extends SingleTypeViewBinder<GoodsShowBean> {


    public GoodsShowViewHinder(Class<GoodsShowBean> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public int onCountView(GoodsShowBean bean, int position) {
        return 0;
    }

    @Override
    public void onBindView(ViewHolder holder, GoodsShowBean bean, int position) {

    }
}
