package cn.junhua.android.commonadapter.binder.taobao;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;
import cn.junhua.android.commonadapter.bean.taobao.GoodsShowBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class GoodsShowViewHinder extends SingleTypeViewBinder<GoodsShowBean> {

    private Context context;

    public GoodsShowViewHinder(Context context) {
        super(GoodsShowBean.class, R.layout.binder_goods_show);
        this.context = context;
    }

    @Override
    public int onCountView(GoodsShowBean bean, int position) {
        return 21;
    }

    @Override
    public void onBindView(ViewHolder holder, GoodsShowBean bean, int position) {
        holder.setText(R.id.tv_goods_title, bean.getTitle())
                .setText(R.id.tv_title1, bean.getTitle1())
                .setText(R.id.tv_title2, bean.getTitle2())
                .setText(R.id.tv_goods_title3, bean.getTitle3())
                .setText(R.id.tv_goods_subtitle3, bean.getSubtitle3())
                .setText(R.id.tv_goods_title4, bean.getTitle4())
                .setText(R.id.tv_goods_subtitle4, bean.getSubtitle4())
                .setText(R.id.tv_goods_title5, bean.getTitle5())
                .setText(R.id.tv_goods_subtitle5, bean.getSubtitle5())
                .setText(R.id.tv_goods_title6, bean.getTitle6())
                .setText(R.id.tv_goods_subtitle6, bean.getSubtitle6());

        setImage(holder, bean.getPhoto1(), R.id.iv_goods_photo1);
        setImage(holder, bean.getPhoto2(), R.id.iv_goods_photo2);
        setImage(holder, bean.getPhoto3_1(), R.id.iv_goods_photo3_1);
        setImage(holder, bean.getPhoto3_2(), R.id.iv_goods_photo3_2);
        setImage(holder, bean.getPhoto4_1(), R.id.iv_goods_photo4_1);
        setImage(holder, bean.getPhoto4_2(), R.id.iv_goods_photo4_2);
        setImage(holder, bean.getPhoto5_1(), R.id.iv_goods_photo5_1);
        setImage(holder, bean.getPhoto5_2(), R.id.iv_goods_photo5_2);
        setImage(holder, bean.getPhoto6_1(), R.id.iv_goods_photo6_1);
        setImage(holder, bean.getPhoto6_2(), R.id.iv_goods_photo6_2);
    }

    private void setImage(ViewHolder holder, String url, int id) {
        ImageView iv_goods_photo1 = holder.findView(id);
        Glide.with(context).load(url).into(iv_goods_photo1);
    }
}
