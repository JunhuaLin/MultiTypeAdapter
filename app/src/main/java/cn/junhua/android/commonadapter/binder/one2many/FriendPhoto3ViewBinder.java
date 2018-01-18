package cn.junhua.android.commonadapter.binder.one2many;

import android.view.View;

import java.util.List;

import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;

/**
 * 朋友圈2-3或5-9张图片
 * Created by linjunhua on 2018/1/6.
 */
public class FriendPhoto3ViewBinder extends FriendPhotoViewBinder {

    private int[] imageViewIds;

    public FriendPhoto3ViewBinder() {
        super(R.layout.binder_friend_photo3);
        imageViewIds = new int[]{
                R.id.iv_photo1,
                R.id.iv_photo2,
                R.id.iv_photo3,

                R.id.iv_photo4,
                R.id.iv_photo5,
                R.id.iv_photo6,

                R.id.iv_photo7,
                R.id.iv_photo8,
                R.id.iv_photo9,
        };
    }

    @Override
    public int onCountView(FriendBean bean, int position) {
        return 3 + 9;
    }

    @Override
    public void onBindImage(ViewHolder holder, FriendBean bean, int position) {
        List<Integer> urls = bean.getPhotos();
        int size = urls.size();
        for (int i = 0; i < imageViewIds.length; i++) {
            if (i < size) {
                setImageRes(holder, imageViewIds[i], urls.get(i));
            } else {
                holder.findView(imageViewIds[i]).setVisibility(((size - 1) / 3 == i / 3) ? View.INVISIBLE : View.GONE);
            }
        }
    }

}
