package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.adapter.binder.MultiTypeViewBinder;
import cn.junhua.android.adapter.binder.SingleTypeViewBinder;
import cn.junhua.android.adapter.imp.OnMatchViewBinder;
import cn.junhua.android.adapter.binder.ViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.binder.RecyclerFirstType1ViewBinder;
import cn.junhua.android.commonadapter.binder.RecyclerFirstType2ViewBinder;
import cn.junhua.android.commonadapter.binder.RecyclerSecondViewBinder;
import cn.junhua.android.commonadapter.binder.RecyclerThreeViewBinder;
import cn.junhua.android.commonadapter.bean.Item1;
import cn.junhua.android.commonadapter.bean.Item2;
import cn.junhua.android.commonadapter.bean.Item3;

/**
 * MultiTypeAdapter demo
 * Created by junhua on 17-3-15.
 */

public class MultiTypeRecyclerViewAdapterActivity extends Activity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter mMultiTypeAdapter;
    private ArrayList<Object> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //再创建CommonRecyclerViewAdapter
        mMultiTypeAdapter = new MultiTypeAdapter(this);
        //注册ViewBinder
        mMultiTypeAdapter.registerViewBinder(new RecyclerSecondViewBinder());
        mMultiTypeAdapter.registerViewBinder(new RecyclerThreeViewBinder());

        //方式一：一对多条目注册
        List<ViewBinder<Item1>> list = new ArrayList<>();
        list.add(new RecyclerFirstType1ViewBinder());
        list.add(new RecyclerFirstType2ViewBinder());
        MultiTypeViewBinder<Item1> multiTypeViewBinder = new MultiTypeViewBinder<>(Item1.class, list, new OnMatchViewBinder<Item1>() {
            @Override
            public Class<? extends ViewBinder<Item1>> onMatch(Item1 bean, int position) {
                if (bean.getType() == 1) {
                    return RecyclerFirstType1ViewBinder.class;
                }
                return RecyclerFirstType2ViewBinder.class;
            }
        });
        mMultiTypeAdapter.registerViewBinder(multiTypeViewBinder);
        mMultiTypeAdapter.unregisterViewBinder(multiTypeViewBinder);

        //方式二：一对多条目注册
        multiTypeViewBinder = mMultiTypeAdapter.registerViewBinder(Item1.class)
                .map(new RecyclerFirstType1ViewBinder(), new RecyclerFirstType2ViewBinder())
                .match(new OnMatchViewBinder<Item1>() {
                    @Override
                    public Class<? extends ViewBinder<Item1>> onMatch(Item1 bean, int position) {
                        if (bean.getType() == 1) {
                            return RecyclerFirstType1ViewBinder.class;
                        }
                        return RecyclerFirstType2ViewBinder.class;
                    }
                });
        mMultiTypeAdapter.unregisterViewBinder(multiTypeViewBinder);

        //设置数据集合
        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "第一类条目的item" + i, i % 2 + 1));
            mDataList.add(new Item2("我是第二类条目", "item" + i));
            mDataList.add(new Item3("我是第3类条目", "item" + i));
        }

        mMultiTypeAdapter.setList(mDataList);

        //为ListView设置Adapter
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(mMultiTypeAdapter);
    }
}
