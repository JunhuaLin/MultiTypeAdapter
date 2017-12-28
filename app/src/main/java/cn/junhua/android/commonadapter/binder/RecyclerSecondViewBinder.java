package cn.junhua.android.commonadapter.binder;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class RecyclerSecondViewBinder extends SingleTypeViewBinder<Item2> {
    public RecyclerSecondViewBinder() {
        super(Item2.class, R.layout.layout_item2);
    }

    @Override
    public void onBindView(ViewHolder holder, Item2 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
