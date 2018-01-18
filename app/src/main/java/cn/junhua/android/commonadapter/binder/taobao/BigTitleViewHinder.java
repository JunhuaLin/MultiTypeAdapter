package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.binder.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.BigTitleBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class BigTitleViewHinder extends SingleViewBinder<BigTitleBean> {


    public BigTitleViewHinder() {
        super(BigTitleBean.class, R.layout.binder_title);
    }

    @Override
    public int onCountView(BigTitleBean bean, int position) {
        return 1;
    }

    @Override
    public void onBindView(ViewHolder holder, BigTitleBean bean, int position) {
        holder.setText(R.id.tv_big_title, bean.getTitle());
    }
}
