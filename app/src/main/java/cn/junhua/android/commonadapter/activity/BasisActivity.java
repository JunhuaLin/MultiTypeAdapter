package cn.junhua.android.commonadapter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.binder.taobao.BannerViewBinder;

/**
 * 基本用法
 * Created by junhua.lin on 2017/12/29.
 */
public class BasisActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MultiTypeAdapter multiTypeAdapter;
    private List<Object> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        //初始化MultiTypeAdapter
        multiTypeAdapter = new MultiTypeAdapter();
        //注册ViewBinder
        multiTypeAdapter.register(new BannerViewBinder());

        multiTypeAdapter.setList(dataList);
        recycler_view.setAdapter(multiTypeAdapter);
    }
}
