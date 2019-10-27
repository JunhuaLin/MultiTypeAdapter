package cn.junhua.android.commonadapter.binder.basis;

import android.view.View;
import android.widget.Toast;

import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;

/**
 * ImgBinderItemView
 * Created by Administrator on 2018/1/21.
 */
public class TextRightBinderItemView extends SimpleItemViewBinder<BasisTextBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BasisTextBean bean, final int position) {
        holder.setText(R.id.tv_type_right, "R.layout.binder_right_text")
                .setText(R.id.tv_class_right, bean.getText());

        holder.findView(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clock item " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_right_text;
    }
}
