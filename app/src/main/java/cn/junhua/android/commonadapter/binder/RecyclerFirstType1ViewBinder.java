package cn.junhua.android.commonadapter.binder;

import android.widget.LinearLayout;

import cn.junhua.android.adapter.recyclerview.SingleTypeViewBinder;
import cn.junhua.android.adapter.recyclerview.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item1;

/**
 * Created by junhua on 17-3-15.
 */

public class RecyclerFirstType1ViewBinder extends SingleTypeViewBinder<Item1> {

    private int[] images = new int[]{
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3
    };

    public RecyclerFirstType1ViewBinder() {
        super(Item1.class, R.layout.layout_item1_type1);
    }

    @Override
    public int onCountView() {
        return 3;
    }

    @Override
    public void onBindView(ViewHolder holder, Item1 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setImage(R.id.icon_iv, bean.getImageId());

        LinearLayout linearLayout = holder.getView(R.id.bg_ll);
        linearLayout.setBackgroundResource(images[position % images.length]);
    }
}
