package cn.junhua.android.commonadapter.binder.basis;

import android.view.View;
import android.widget.Toast;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;

/**
 * ImgBinderView
 * Created by Administrator on 2018/1/21.
 */
public class TextRightBinderView extends SingleViewBinder<BasisTextBean> {

    public TextRightBinderView() {
        super(BasisTextBean.class, R.layout.binder_right_text);
    }

    @Override
    public int onCountView(BasisTextBean bean, int position) {
        return 3;
    }

    @Override
    public void onBindView(ViewHolder holder, BasisTextBean bean, final int position) {
        holder.setText(R.id.tv_type_right, "R.layout.binder_right_text")
                .setText(R.id.tv_class_right, bean.getText());

        holder.findView(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clock item " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
