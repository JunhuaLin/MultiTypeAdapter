package cn.junhua.android.commonadapter.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.hint.HintBean;
import cn.junhua.android.commonadapter.bean.taobao.BannerBean;
import cn.junhua.android.commonadapter.bean.taobao.BigTitleBean;
import cn.junhua.android.commonadapter.bean.taobao.GoodsShowBean;
import cn.junhua.android.commonadapter.bean.taobao.LikeBean;
import cn.junhua.android.commonadapter.bean.taobao.MenuBean;
import cn.junhua.android.commonadapter.binder.hint.HintItemViewBinder;
import cn.junhua.android.commonadapter.binder.defaults.DefaultItemViewBinder;
import cn.junhua.android.commonadapter.binder.taobao.BigTitleItemViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.GoodsShowItemViewHinder;
import cn.junhua.android.commonadapter.binder.taobao.MenuItemViewHinder;

/**
 * 淘宝首页
 * Created by junhua.lin on 2017/12/29.
 */
public class DefaultViewBinderActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MultiTypeAdapter multiTypeAdapter;
    private List<Object> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recycler_view = findViewById(R.id.recycler_view);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //初始化MultiTypeAdapter
        multiTypeAdapter = new MultiTypeAdapter();
        //注册ViewBinder
        multiTypeAdapter.register(HintBean.class, new HintItemViewBinder());//一对一
        multiTypeAdapter.register(MenuBean.class, new MenuItemViewHinder());
        multiTypeAdapter.register(GoodsShowBean.class, new GoodsShowItemViewHinder());
        multiTypeAdapter.register(BigTitleBean.class, new BigTitleItemViewHinder());
        multiTypeAdapter.setDefaultViewBinder(new DefaultItemViewBinder());

        genDataList(true);
        multiTypeAdapter.setList(dataList);
        recycler_view.setAdapter(multiTypeAdapter);
        multiTypeAdapter.notifyDataSetChanged();

        new Handler().postDelayed(() -> {
            genDataList(false);
            multiTypeAdapter.notifyDataSetChanged();
            Toast.makeText(DefaultViewBinderActivity.this, "追加数据", Toast.LENGTH_SHORT).show();
        }, 5000);
    }

    public void genDataList(boolean isFirst) {
        if (!isFirst) {
            dataList.add(new HintBean());
        }

        dataList.add(new BannerBean());
        dataList.add(new MenuBean());

        for (int i = 0; i < 2; i++) {
            dataList.add(new GoodsShowBean());
        }

        dataList.add(new BigTitleBean("猜你喜欢"));
        for (int i = 0; i < 10; i++) {
            dataList.add(new LikeBean());
        }
    }
}
