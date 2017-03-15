package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.CommonBaseAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.adapter.ListViewFirstViewBinder;
import cn.junhua.android.commonadapter.adapter.ListViewSecondViewBinder;
import cn.junhua.android.commonadapter.bean.Item1;
import cn.junhua.android.commonadapter.bean.Item2;

/**
 * Created by junhua on 17-3-15.
 */

public class CommonBaseAdapterActivity extends Activity {

    private ListView list_view;
    private CommonBaseAdapter mCommonBaseAdapter;
    private List<Object> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        list_view = (ListView) findViewById(R.id.list_view);

        //创建ViewBinder
        List<CommonBaseAdapter.ViewBinder> viewBinders = new ArrayList<>();
        viewBinders.add(new ListViewFirstViewBinder(Item1.class, R.layout.layout_item1));
        viewBinders.add(new ListViewSecondViewBinder(Item2.class, R.layout.layout_item2));

        //在创建CommonBaseAdapter
        mCommonBaseAdapter = new CommonBaseAdapter(this, viewBinders);

        //设置数据集合
        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "第一类条目的item" + i));
            mDataList.add(new Item2("我是第二类条目", "item" + i));
        }
        mCommonBaseAdapter.setList(mDataList);

        //为ListView设置Adapter
        list_view.setAdapter(mCommonBaseAdapter);
    }
}
