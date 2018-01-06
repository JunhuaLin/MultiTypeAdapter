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
import cn.junhua.android.commonadapter.binder.taobao.BannerViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.GoodsShowViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.LikeViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.MenuViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.BigTitleViewHinder;
import cn.junhua.android.commonadapter.imp.SpanSize;

/**
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
        for (int i = 0; i < 20; i++) {
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
        multiTypeAdapter = new MultiTypeAdapter(this);
        //注册ViewBinder
        multiTypeAdapter.registerViewBinder(new BannerViewHinder());
        multiTypeAdapter.registerViewBinder(new MenuViewHinder());
        multiTypeAdapter.registerViewBinder(new LikeViewHinder());
        multiTypeAdapter.registerViewBinder(new GoodsShowViewHinder());
        multiTypeAdapter.registerViewBinder(new BigTitleViewHinder());

        multiTypeAdapter.setList(dataList);
        recycler_view.setAdapter(multiTypeAdapter);
    }
}
