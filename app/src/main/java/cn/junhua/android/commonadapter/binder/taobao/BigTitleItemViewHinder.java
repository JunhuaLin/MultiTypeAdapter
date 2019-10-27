package cn.junhua.android.commonadapter.binder.taobao;

import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.BigTitleBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class BigTitleItemViewHinder extends SimpleItemViewBinder<BigTitleBean> {

    @Override
    protected int getLayoutId() {
        return R.layout.binder_title;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BigTitleBean bean, int position) {
        holder.setText(R.id.tv_big_title, bean.getTitle());
    }
}
