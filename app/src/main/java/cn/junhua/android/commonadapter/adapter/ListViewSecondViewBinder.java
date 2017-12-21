package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.CommonBaseAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class ListViewSecondViewBinder extends CommonBaseAdapter.ViewBinder<Item2> {
    public ListViewSecondViewBinder(Class<Item2> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void onBindView(CommonBaseAdapter.ViewHolder holder, Item2 bean, int position) {
        holder.setText(R.id.title_tv, bean.getTitle())
                .setText(R.id.info_tv, bean.getInfo());
    }
}
