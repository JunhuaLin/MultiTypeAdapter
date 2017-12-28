package cn.junhua.android.commonadapter.binder;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item3;

/**
 * Created by junhua.lin on 2017/12/21.
 */

public class RecyclerThreeViewBinder extends SingleTypeViewBinder<Item3> {
    public RecyclerThreeViewBinder() {
        super(Item3.class, R.layout.layout_item3);
    }

    @Override
    public void onBindView(ViewHolder holder, Item3 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
