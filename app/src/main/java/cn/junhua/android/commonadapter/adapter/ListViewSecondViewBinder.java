package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.listview.CommonBaseAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class ListViewSecondViewBinder extends CommonBaseAdapter.ViewBinder<Item2> {
    public ListViewSecondViewBinder() {
        super(Item2.class, R.layout.layout_item2);
    }

    @Override
    public void onBindView(CommonBaseAdapter.ViewHolder holder, Item2 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
