package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.adapter.binder.MultiTypeViewBinder;
import cn.junhua.android.adapter.imp.OnMatchListener;
import cn.junhua.android.adapter.binder.ViewBinder;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.binder.one2many.RecyclerFirstType1ViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.RecyclerFirstType2ViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.RecyclerSecondViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.RecyclerThreeViewBinder;
import cn.junhua.android.commonadapter.bean.one2many.Item1;
import cn.junhua.android.commonadapter.bean.one2many.Item2;
import cn.junhua.android.commonadapter.bean.one2many.Item3;

/**
 * MultiTypeAdapter demo
 * Created by junhua on 17-3-15.
 */

public class One2ManyActivity extends Activity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter mMultiTypeAdapter;
    private ArrayList<Object> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //再创建MultiTypeAdapter
        mMultiTypeAdapter = new MultiTypeAdapter(this);
        //注册ViewBinder
        mMultiTypeAdapter.registerViewBinder(new RecyclerSecondViewBinder());
        mMultiTypeAdapter.registerViewBinder(new RecyclerThreeViewBinder());

        //方式一：一对多条目注册
        List<ViewBinder<Item1>> list = new ArrayList<>();
        list.add(new RecyclerFirstType1ViewBinder());
        list.add(new RecyclerFirstType2ViewBinder());
        MultiTypeViewBinder<Item1> multiTypeViewBinder = new MultiTypeViewBinder<>(Item1.class, list, new OnMatchListener<Item1>() {
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

        //方式二：一对多条目注册(推荐)
        multiTypeViewBinder = mMultiTypeAdapter.registerViewBinder(Item1.class)
                .mapping(new RecyclerFirstType1ViewBinder(), new RecyclerFirstType2ViewBinder())
                .match(new OnMatchListener<Item1>() {
                    @Override
                    public Class<? extends ViewBinder<Item1>> onMatch(Item1 bean, int position) {
                        if (bean.getType() == 1) {
                            return RecyclerFirstType1ViewBinder.class;
                        }
                        return RecyclerFirstType2ViewBinder.class;
                    }
                });
//        mMultiTypeAdapter.unregisterViewBinder(multiTypeViewBinder);

        //设置数据集合
        mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, Item1.class.getSimpleName() + "条目", R.layout.layout_item1_type1));
            mDataList.add(new Item2(Item2.class.getSimpleName() + "条目", "item"));
            for (int j = 0; j < 10; j++) {
                mDataList.add(new Item3(Item3.class.getSimpleName() + "条目", "item"));
            }
        }

        mMultiTypeAdapter.setList(mDataList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mDataList.get(position).getClass() == Item3.class) {
                    return 1;
                }
                return gridLayoutManager.getSpanCount();
            }
        });

//        recycler_view.setLayoutManager(gridLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recycler_view.setLayoutManager(staggeredGridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setAdapter(mMultiTypeAdapter);
    }
}
