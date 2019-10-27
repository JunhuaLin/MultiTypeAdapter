package cn.junhua.android.commonadapter.binder.basis;

import cn.junhua.android.adapter.CommonViewHolder;
import cn.junhua.android.adapter.SimpleItemViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisImgBean;

/**
 * ImgBinderItemView
 * Created by Administrator on 2018/1/21.
 */
public class ImgBinderItemView extends SimpleItemViewBinder<BasisImgBean> {

    @Override
    public void onBindViewHolder(CommonViewHolder holder, BasisImgBean bean, int position) {
        holder.setText(R.id.tv_type_content, "BasisImgBean.class" + "->R.layout.binder_basis_img")
                .setImageResource(R.id.iv_img, bean.getRes());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.binder_basis_img;
    }
}
