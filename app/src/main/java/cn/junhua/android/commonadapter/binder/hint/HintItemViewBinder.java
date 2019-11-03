package cn.junhua.android.commonadapter.binder.hint;

import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.hint.HintBean;

/**
 * 追加的数据提示
 * Created by junhua.lin on 2018/3/29.
 */
public class HintItemViewBinder extends SimpleItemViewBinder<HintBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, HintBean bean, int position) {
        holder.setText(R.id.tv_hint, "以下为追加的数据");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hint_binder_layoutt;
    }
}
