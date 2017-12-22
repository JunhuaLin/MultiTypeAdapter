package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.CommonBaseAdapter;
import cn.junhua.android.adapter.CommonRecyclerViewAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class RecyclerSecondViewBinder extends CommonRecyclerViewAdapter.ViewBinder<Item2> {
    public RecyclerSecondViewBinder() {
        super(Item2.class, R.layout.layout_item2);
    }

    @Override
    public void onBindView(CommonRecyclerViewAdapter.ViewHolder holder, Item2 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
