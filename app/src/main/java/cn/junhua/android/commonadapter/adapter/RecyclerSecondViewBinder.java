package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.CommonBaseAdapter;
import cn.junhua.android.adapter.CommonRecyclerViewAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class RecyclerSecondViewBinder extends CommonRecyclerViewAdapter.ViewBinder {
    public RecyclerSecondViewBinder(Class<?> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void bindView(CommonRecyclerViewAdapter.ViewHolder holder, Object bean, int position) {
        Item2 item = (Item2) bean;
        holder.setText(R.id.title_tv, item.getTitle())
                .setText(R.id.info_tv, item.getInfo());
    }
}
