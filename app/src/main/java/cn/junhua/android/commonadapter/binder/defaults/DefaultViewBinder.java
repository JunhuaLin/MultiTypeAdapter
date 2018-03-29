package cn.junhua.android.commonadapter.binder.defaults;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;

/**
 * default
 * Created by junhua.lin on 2018/3/29.
 */
public class DefaultViewBinder extends SingleViewBinder<Object> {

    public DefaultViewBinder() {
        super(Object.class, R.layout.binder_default);
    }

    @Override
    public void onBindView(ViewHolder holder, Object bean, int position) {
        holder.setText(R.id.tv_default_hint, "没有对应ViewBinder 默认ViewBinder展示：" + bean.getClass().getSimpleName()+".class");
    }
}
