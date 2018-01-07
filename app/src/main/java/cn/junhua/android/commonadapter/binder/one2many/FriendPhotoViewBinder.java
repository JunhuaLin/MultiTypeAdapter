package cn.junhua.android.commonadapter.binder.one2many;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;

import cn.junhua.android.adapter.binder.SingleViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;

/**
 * 朋友圈图片
 * Created by linjunhua on 2018/1/6.
 */
public abstract class FriendPhotoViewBinder extends SingleViewBinder<FriendBean> {

    public FriendPhotoViewBinder(int layoutId) {
        super(FriendBean.class, layoutId);
    }

    @Override
    public int onCountView(FriendBean bean, int position) {
        return 3 + bean.getPhotos().size();
    }

    @Override
    public void onBindView(ViewHolder holder, FriendBean bean, int position) {
        holder.setText(R.id.tv_name, bean.getName())
                .setText(R.id.tv_content, bean.getContent());

        setImageRes(holder, R.id.iv_avatar, bean.getAvatar());

        onBindImage(holder, bean, position);
    }

    /**
     * 仅处理晒图图片的绑定
     */
    public abstract void onBindImage(ViewHolder holder, FriendBean bean, int position);

    public void setImageRes(ViewHolder holder, @IdRes int id,@DrawableRes int mipmap) {
        ImageView imageView = holder.findView(id);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(mipmap);
    }
}
