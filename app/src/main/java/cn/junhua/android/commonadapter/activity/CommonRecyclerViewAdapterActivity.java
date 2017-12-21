package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.CommonRecyclerViewAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.adapter.RecyclerFirstViewBinder;
import cn.junhua.android.commonadapter.adapter.RecyclerSecondViewBinder;
import cn.junhua.android.commonadapter.adapter.RecyclerThreeViewBinder;
import cn.junhua.android.commonadapter.bean.Item1;
import cn.junhua.android.commonadapter.bean.Item2;
import cn.junhua.android.commonadapter.bean.Item3;

/**
 * Created by junhua on 17-3-15.
 */

public class CommonRecyclerViewAdapterActivity extends Activity {

    private RecyclerView recycler_view;
    private CommonRecyclerViewAdapter mCommonRecyclerViewAdapter;
    private ArrayList<Object> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //创建ViewBinder
        List<CommonRecyclerViewAdapter.ViewBinder> viewBinders = new ArrayList<>();
        viewBinders.add(new RecyclerFirstViewBinder(Item1.class, R.layout.layout_item1));
        viewBinders.add(new RecyclerSecondViewBinder(Item2.class, R.layout.layout_item2));
        viewBinders.add(new RecyclerThreeViewBinder(Item3.class, R.layout.layout_item3));

        //再创建CommonRecyclerViewAdapter
        mCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter(this, viewBinders);

        //设置数据集合
        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "第一类条目的item" + i));
            mDataList.add(new Item2("我是第二类条目", "item" + i));
            mDataList.add(new Item3("我是第3类条目", "item" + i));
        }

        mCommonRecyclerViewAdapter.setList(mDataList);

        //为ListView设置Adapter
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(mCommonRecyclerViewAdapter);
    }
}
