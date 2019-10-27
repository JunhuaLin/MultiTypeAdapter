package cn.junhua.android.commonadapter.binder.defaults;

import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.commonadapter.R;

/**
 * default
 * Created by junhua.lin on 2018/3/29.
 */
public class DefaultItemViewBinder extends SimpleItemViewBinder<Object> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, Object bean, int position) {
        holder.setText(R.id.tv_default_hint, "没有对应ViewBinder 默认ViewBinder展示：" + bean.getClass().getSimpleName() + ".class");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_default;
    }
}
