package cn.junhua.android.commonadapter.binder.taobao;

import com.bumptech.glide.Glide;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.LikeBean;
import cn.junhua.android.commonadapter.view.RectImageView;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class LikeViewHinder extends SingleViewBinder<LikeBean> {

    public LikeViewHinder() {
        super(LikeBean.class, R.layout.binder_like);
    }


    @Override
    public int onCountView(LikeBean bean, int position) {
        return 4;
    }

    @Override
    public void onBindView(ViewHolder holder, LikeBean bean, int position) {
        RectImageView riv_goods = holder.findView(R.id.riv_goods);
        Glide.with(riv_goods.getContext()).load(bean.getUrl()).into(riv_goods);

        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_price, "￥" + bean.getPrice())
                .setText(R.id.tv_count, bean.getCount() + "人付款");
    }
}
