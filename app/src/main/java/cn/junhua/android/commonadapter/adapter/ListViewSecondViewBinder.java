package cn.junhua.android.commonadapter.adapter;

import cn.junhua.android.adapter.CommonBaseAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class ListViewSecondViewBinder extends CommonBaseAdapter.ViewBinder {
    public ListViewSecondViewBinder(Class<?> beanClass, int layoutId) {
        super(beanClass, layoutId);
    }

    @Override
    public void bindView(CommonBaseAdapter.ViewHolder holder, Object bean, int position) {
        Item2 item = (Item2) bean;
        holder.setText(R.id.title_tv, item.getTitle())
                .setText(R.id.info_tv, item.getInfo());
    }
}
