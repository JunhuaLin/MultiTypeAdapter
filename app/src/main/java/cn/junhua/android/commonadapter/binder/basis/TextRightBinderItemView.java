package cn.junhua.android.commonadapter.binder.basis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.junhua.android.adapter.ItemViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;

/**
 * ImgBinderItemView
 * Created by Administrator on 2018/1/21.
 */
public class TextRightBinderItemView extends ItemViewBinder<BasisTextBean, TextRightBinderItemView.TextRightViewHolder> {

    @Override
    public TextRightViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new TextRightViewHolder(inflater.inflate(R.layout.binder_right_text, parent, false));
    }

    @Override
    public void onBindViewHolder(TextRightViewHolder holder, BasisTextBean bean, int position) {
        holder.bindData(bean, position);
    }

    class TextRightViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type_right;
        TextView tv_class_right;

        TextRightViewHolder(View itemView) {
            super(itemView);
            tv_type_right = itemView.findViewById(R.id.tv_type_right);
            tv_class_right = itemView.findViewById(R.id.tv_class_right);
        }

        void bindData(BasisTextBean bean, int position) {
            tv_type_right.setText("R.layout.binder_right_text");
            tv_class_right.setText(bean.getText());
            itemView.setOnClickListener(view -> Toast.makeText(view.getContext(), "TextRightBinderItemView item " + position, Toast.LENGTH_SHORT).show());
        }
    }
}
