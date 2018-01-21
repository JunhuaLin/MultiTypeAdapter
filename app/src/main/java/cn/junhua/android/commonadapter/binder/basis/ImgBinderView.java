package cn.junhua.android.commonadapter.binder.basis;

import cn.junhua.android.adapter.SingleViewBinder;
import cn.junhua.android.adapter.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisImgBean;

/**
 * ImgBinderView
 * Created by Administrator on 2018/1/21.
 */
public class ImgBinderView extends SingleViewBinder<BasisImgBean> {

    public ImgBinderView() {
        super(BasisImgBean.class, R.layout.binder_basis_img);
    }

    @Override
    public void onBindView(ViewHolder holder, BasisImgBean bean, int position) {
        holder.setText(R.id.tv_type_content, "BasisImgBean.class" + "->R.layout.binder_basis_img")
                .setImageResource(R.id.iv_img, bean.getRes());
    }
}
