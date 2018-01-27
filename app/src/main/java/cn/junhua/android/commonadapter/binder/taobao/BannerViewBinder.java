package cn.junhua.android.commonadapter.binder.taobao;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;

/**
 * 淘宝banner
 * Created by junhua.lin on 2017/12/29.
 */
public class BannerViewBinder extends SingleViewBinder<BannerBean> {

    public BannerViewBinder() {
        super(BannerBean.class, R.layout.binder_banner);
    }

    @Override
    public int onCountView(BannerBean bean, int position) {
        return 2;
    }

    @Override
    public void onBindView(ViewHolder holder, BannerBean bean, int position) {
        Banner banner = holder.findView(R.id.banner);

        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        };

        //设置图片加载器
        banner.setImageLoader(imageLoader);
        //设置图片集合
        banner.setImages(bean.getUrlList());
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    protected long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    protected void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    protected boolean onFailedToRecycleView(ViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    protected void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    protected void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
