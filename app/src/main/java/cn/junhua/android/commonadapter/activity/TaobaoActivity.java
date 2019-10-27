package cn.junhua.android.commonadapter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;
import cn.junhua.android.commonadapter.bean.taobao.BigTitleBean;
import cn.junhua.android.commonadapter.bean.taobao.GoodsShowBean;
import cn.junhua.android.commonadapter.bean.taobao.LikeBean;
import cn.junhua.android.commonadapter.bean.taobao.MenuBean;
import cn.junhua.android.commonadapter.binder.taobao.BannerItemViewBinder;
import cn.junhua.android.commonadapter.binder.taobao.BigTitleItemViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.GoodsShowItemViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.LikeItemViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.MenuItemViewHinder;
import cn.junhua.android.commonadapter.imp.SpanSize;

/**
 * 淘宝首页
 * Created by junhua.lin on 2017/12/29.
 */
public class TaobaoActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MultiTypeAdapter multiTypeAdapter;
    private List<Object> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        dataList = new ArrayList<>();
        dataList.add(new BannerBean());
        dataList.add(new MenuBean());

        for (int i = 0; i < 2; i++) {
            dataList.add(new GoodsShowBean());
        }

        dataList.add(new BigTitleBean("猜你喜欢"));
        for (int i = 0; i < 10; i++) {
            dataList.add(new LikeBean());
        }

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object object = dataList.get(position);
                if (object instanceof SpanSize) {
                    return ((SpanSize) object).getSpanSize();
                }
                return 2;
            }
        });
        recycler_view.setLayoutManager(gridLayoutManager);

        //初始化MultiTypeAdapter
        multiTypeAdapter = new MultiTypeAdapter();
        //注册ViewBinder
        multiTypeAdapter.register(BannerBean.class, new BannerItemViewBinder());
        multiTypeAdapter.register(MenuBean.class, new MenuItemViewHinder());
        multiTypeAdapter.register(LikeBean.class, new LikeItemViewHinder());
        multiTypeAdapter.register(GoodsShowBean.class, new GoodsShowItemViewHinder());
        multiTypeAdapter.register(BigTitleBean.class, new BigTitleItemViewHinder());

        multiTypeAdapter.setList(dataList);
        recycler_view.setAdapter(multiTypeAdapter);
    }
}
