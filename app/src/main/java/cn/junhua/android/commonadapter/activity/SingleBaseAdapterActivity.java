package cn.junhua.android.commonadapter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.adapter.ListViewSingleAdapter;
import cn.junhua.android.commonadapter.bean.Item1;

/**
 * Created by junhua on 17-3-15.
 */

public class SingleBaseAdapterActivity extends Activity {

    private ListViewSingleAdapter mListViewSingleAdapter;
    private ListView mListView;
    private List<Item1> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = (ListView) findViewById(R.id.list_view);

        mListViewSingleAdapter = new ListViewSingleAdapter(this, R.layout.layout_item1_type1);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add(new Item1(R.mipmap.ic_launcher, "item " + i));
        }
        mListViewSingleAdapter.setList(mDataList);
        mListView.setAdapter(mListViewSingleAdapter);
    }
}
