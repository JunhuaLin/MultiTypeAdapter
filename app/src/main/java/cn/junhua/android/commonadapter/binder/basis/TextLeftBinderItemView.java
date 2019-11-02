package cn.junhua.android.commonadapter.binder.basis;

import android.widget.Toast;

import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;

/**
 * ImgBinderItemView
 * Created by Administrator on 2018/1/21.
 */
public class TextLeftBinderItemView extends SimpleItemViewBinder<BasisTextBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BasisTextBean bean, int position) {
        holder.setText(R.id.tv_layout_left, "R.layout.binder_left_text")
                .setText(R.id.tv_class_left, bean.getText())
                .setOnItemClickListener(view -> Toast.makeText(view.getContext(),
                        "TextLeftBinderItemView item " + position,
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_left_text;
    }
}
