package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.CommonRecyclerViewAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item3;

/**
 * Created by junhua.lin on 2017/12/21.
 */

public class RecyclerThreeViewBinder extends CommonRecyclerViewAdapter.ViewBinder<Item3> {
    public RecyclerThreeViewBinder(Class<Item3> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void onBindView(CommonRecyclerViewAdapter.ViewHolder holder, Item3 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
