package cn.junhua.android.commonadapter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.adapter.binder.MultiViewBinder;
import cn.junhua.android.adapter.ViewBinder;
import cn.junhua.android.adapter.imp.TypeMatcher;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.one2many.FriendBean;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto1ViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto3ViewBinder;
import cn.junhua.android.commonadapter.binder.one2many.FriendPhoto4ViewBinder;

/**
 * MultiTypeAdapter demo
 * Created by junhua on 17-3-15.
 */

public class One2ManyActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private MultiTypeAdapter mMultiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        //再创建MultiTypeAdapter
        mMultiTypeAdapter = new MultiTypeAdapter(this);
        //注册ViewBinder
        //方式一：一对多条目注册(不推荐)
        List<ViewBinder<FriendBean>> list = new ArrayList<>();
        list.add(new FriendPhoto1ViewBinder());
        list.add(new FriendPhoto3ViewBinder());
        list.add(new FriendPhoto4ViewBinder());
        MultiViewBinder<FriendBean> multiViewBinder = new MultiViewBinder<>(FriendBean.class, list, new TypeMatcher<FriendBean>() {
            @Override
            public Class<? extends ViewBinder<FriendBean>> onMatch(FriendBean bean, int position) {
                int size = bean.getPhotos().size();
                if (size == 0 || size == 1) return FriendPhoto1ViewBinder.class;
                if (size == 4) return FriendPhoto4ViewBinder.class;
                return FriendPhoto3ViewBinder.class;
            }
        });
        mMultiTypeAdapter.register(multiViewBinder);
        mMultiTypeAdapter.unregister(multiViewBinder);

        //方式二：一对多条目注册(推荐)
        mMultiTypeAdapter.register(FriendBean.class)
                .mapping(new FriendPhoto1ViewBinder(),
                        new FriendPhoto3ViewBinder(),
                        new FriendPhoto4ViewBinder())
                .match(new TypeMatcher<FriendBean>() {
                    @Override
                    public Class<? extends ViewBinder<FriendBean>> onMatch(FriendBean bean, int position) {
                        int size = bean.getPhotos().size();
                        if (size == 0 || size == 1) return FriendPhoto1ViewBinder.class;
                        if (size == 4) return FriendPhoto4ViewBinder.class;
                        return FriendPhoto3ViewBinder.class;
                    }
                });

        mMultiTypeAdapter.setList(FriendBean.getRandomFriend());
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler_view.setAdapter(mMultiTypeAdapter);
    }
}
