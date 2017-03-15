package cn.junhua.android.commonadapter.adapter;

import android.widget.LinearLayout;

import cn.junhua.android.adapter.CommonRecyclerViewAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item1;

/**
 * Created by junhua on 17-3-15.
 */

public class RecyclerFirstViewBinder extends CommonRecyclerViewAdapter.ViewBinder {

    private int[] images = new int[]{
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3
    };

    public RecyclerFirstViewBinder(Class<?> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void bindView(CommonRecyclerViewAdapter.ViewHolder holder, Object bean, int position) {
        Item1 item = (Item1) bean;

        holder.setText(R.id.title_tv, item.getTitle())
                .setImage(R.id.icon_iv, item.getImageId());

        LinearLayout linearLayout = holder.getView(R.id.bg_ll);
        linearLayout.setBackgroundResource(images[position % images.length]);

    }

}
