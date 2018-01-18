package cn.junhua.android.commonadapter.binder.one2many;

import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;

/**
 * 朋友圈4张图片
 * Created by linjunhua on 2018/1/6.
 */
public class FriendPhoto4ViewBinder extends FriendPhotoViewBinder {

    private int[] imageViewIds;

    public FriendPhoto4ViewBinder() {
        super(R.layout.binder_friend_photo4);
        imageViewIds = new int[]{
                R.id.iv_photo1,
                R.id.iv_photo2,
                R.id.iv_photo3,
                R.id.iv_photo4
        };
    }

    @Override
    public void onBindImage(ViewHolder holder, FriendBean bean, int position) {
        for (int i = 0; i < imageViewIds.length; i++) {
            setImageRes(holder, imageViewIds[i], bean.getPhotos().get(i));
        }
    }
}
