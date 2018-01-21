package cn.junhua.android.commonadapter.binder.basis;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;

/**
 * ImgBinderView
 * Created by Administrator on 2018/1/21.
 */
public class TextLeftBinderView extends SingleViewBinder<BasisTextBean> {

    public TextLeftBinderView() {
        super(BasisTextBean.class, R.layout.binder_left_text);
    }

    @Override
    public int onCountView(BasisTextBean bean, int position) {
        return 2;
    }

    @Override
    public void onBindView(ViewHolder holder, BasisTextBean bean, int position) {
        holder.setText(R.id.tv_layout_left, "R.layout.binder_left_text")
                .setText(R.id.tv_class_left, bean.getText());
    }
}
