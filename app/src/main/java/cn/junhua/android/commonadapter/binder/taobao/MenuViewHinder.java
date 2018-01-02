package cn.junhua.android.commonadapter.binder.taobao;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.binder.ViewHolder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.MenuBean;

/**
 * 淘宝菜单
 * Created by junhua.lin on 2017/12/29.
 */
public class MenuViewHinder extends SingleTypeViewBinder<MenuBean> {
    private Context context;
    private int[] ids = new int[]{
            R.id.iv_menu1,
            R.id.iv_menu2,
            R.id.iv_menu3,
            R.id.iv_menu4,
            R.id.iv_menu5,

            R.id.iv_menu6,
            R.id.iv_menu7,
            R.id.iv_menu8,
            R.id.iv_menu9,
            R.id.iv_menu10,
    };

    public MenuViewHinder(Context context) {
        super(MenuBean.class, R.layout.binder_menu);
        this.context = context;
    }

    @Override
    public int onCountView(MenuBean bean, int position) {
        return 10;
    }

    @Override
    public void onBindView(ViewHolder holder, MenuBean bean, int position) {
        for (int id : ids) {
            setSrc(holder, bean, id);
        }
    }

    private void setSrc(ViewHolder holder, MenuBean bean, int id) {
        ImageView iv_menu = holder.findView(id);
        Glide.with(context).load(bean.getUrl()).into(iv_menu);
    }
}
