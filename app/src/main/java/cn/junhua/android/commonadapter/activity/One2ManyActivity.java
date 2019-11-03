package cn.junhua.android.commonadapter.activity;

import android.os.Bundle;
import android.os.Handler;
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
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;
import cn.junhua.android.commonadapter.binder.hint.HintItemViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto1ItemViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto3ItemViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto4ItemViewBinder;

/**
 * 朋友圈效果
 * Created by junhua on 17-3-15.
 */

public class One2ManyActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter mMultiTypeAdapter;
    private List<Object> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //再创建MultiTypeAdapter
        mMultiTypeAdapter = new MultiTypeAdapter();
        //注册ViewBinder
        mMultiTypeAdapter.register(HintBean.class, new HintItemViewBinder());//一对一
        //一对多条目注册
        mMultiTypeAdapter.register(FriendBean.class)
                .mapping(new FriendPhoto1ItemViewBinder(),
                        new FriendPhoto3ItemViewBinder(),
                        new FriendPhoto4ItemViewBinder())
                .match((bean, position) -> {
                    int size = bean.getPhotos().size();
                    if (size == 0 || size == 1) return FriendPhoto1ItemViewBinder.class;
                    if (size == 4) return FriendPhoto4ItemViewBinder.class;
                    return FriendPhoto3ItemViewBinder.class;
                });

        mList.addAll(FriendBean.getRandomFriend());

        mMultiTypeAdapter.setList(mList);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler_view.setAdapter(mMultiTypeAdapter);

        new Handler().postDelayed(() -> {
            mList.add(new HintBean());
            mList.addAll(FriendBean.getRandomFriend());
            mMultiTypeAdapter.notifyDataSetChanged();
            Toast.makeText(One2ManyActivity.this, "追加数据", Toast.LENGTH_SHORT).show();
        }, 3000);
    }
}
