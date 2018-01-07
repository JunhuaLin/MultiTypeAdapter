package cn.junhua.android.commonadapter.binder.one2many;

import android.view.View;

import java.util.List;

import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;

/**
 * 朋友圈0-1张图片
 * Created by linjunhua on 2018/1/6.
 */
public class FriendPhoto1ViewBinder extends FriendPhotoViewBinder {

    public FriendPhoto1ViewBinder() {
        super(R.layout.binder_friend_photo1);
    }

    @Override
    public void onBindImage(ViewHolder holder, FriendBean bean, int position) {
        List<Integer> urls = bean.getPhotos();
        int size = urls.size();
        if (1 == size) {
            setImageRes(holder, R.id.iv_photo1, urls.get(0));
        } else {
            holder.findView(R.id.iv_photo1).setVisibility(View.GONE);
        }
    }
}
