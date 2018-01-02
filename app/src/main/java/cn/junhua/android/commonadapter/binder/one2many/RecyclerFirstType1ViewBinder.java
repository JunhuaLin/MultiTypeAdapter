package cn.junhua.android.commonadapter.binder.one2many;

import android.widget.TextView;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.Item1;

/**
 * Created by junhua on 17-3-15.
 */
public class RecyclerFirstType1ViewBinder extends SingleTypeViewBinder<Item1> {

    public RecyclerFirstType1ViewBinder() {
        super(Item1.class, R.layout.layout_item1_type1);
    }

    @Override
    public int onCountView(Item1 bean, int position) {
        return 3;
    }

    @Override
    public void onBindView(ViewHolder holder, Item1 bean, int position) {
        //通用方法
        TextView title_tv = holder.findView(R.id.title_tv);
        title_tv.setText(bean.getTitle());
        //便捷方法
        holder.setText(R.id.title_tv, bean.getTitle() + "  layout_item1_type1")
                .setImageResource(R.id.icon_iv, bean.getImageId())
                .setBackgroundResource(R.id.bg_ll, R.mipmap.image1);
    }
}
