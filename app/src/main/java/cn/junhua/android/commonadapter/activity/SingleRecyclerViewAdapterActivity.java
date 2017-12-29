package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.Item1;
import cn.junhua.android.commonadapter.binder.RecyclerViewSingleBinder;

/**
 * Created by junhua on 17-3-15.
 */
public class SingleRecyclerViewAdapterActivity extends Activity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter multiTypeAdapter;
    private ArrayList<Item1> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        multiTypeAdapter = new MultiTypeAdapter(this);
        multiTypeAdapter.registerViewBinder(new RecyclerViewSingleBinder());
        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "item " + i));
        }
        multiTypeAdapter.setList(mDataList);
        recycler_view.setAdapter(multiTypeAdapter);
    }
}
