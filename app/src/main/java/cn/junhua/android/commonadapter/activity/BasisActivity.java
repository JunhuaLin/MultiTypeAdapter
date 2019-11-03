package cn.junhua.android.commonadapter.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.junhua.android.adapter.MultiTypeAdapter;
import cn.junhua.android.commonadapter.R;
import cn.junhua.android.commonadapter.bean.hint.HintBean;
import cn.junhua.android.commonadapter.bean.basis.BasisImgBean;
import cn.junhua.android.commonadapter.bean.basis.BasisTextBean;
import cn.junhua.android.commonadapter.binder.hint.HintItemViewBinder;
import cn.junhua.android.commonadapter.binder.basis.ImgBinderItemView;
import cn.junhua.android.commonadapter.binder.basis.TextLeftBinderItemView;
import cn.junhua.android.commonadapter.binder.basis.TextRightBinderItemView;

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

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(2, 2, 2, 2);
            }
        });
        //初始化MultiTypeAdapter
        multiTypeAdapter = new MultiTypeAdapter();
        //注册ViewBinder
        multiTypeAdapter.register(HintBean.class, new HintItemViewBinder());//一对一
        multiTypeAdapter.register(BasisImgBean.class, new ImgBinderItemView());//一对一
        multiTypeAdapter.register(BasisTextBean.class)//一对多
                .mapping(new TextLeftBinderItemView(), new TextRightBinderItemView())
                .match((bean, position) -> {
                    if (bean.getType() == 1) return TextLeftBinderItemView.class;
                    return TextRightBinderItemView.class;
                });

        genDataList(true);
        multiTypeAdapter.setList(dataList);
        recycler_view.setAdapter(multiTypeAdapter);
        multiTypeAdapter.notifyDataSetChanged();


        new Handler().postDelayed(() -> {
            genDataList(false);
            multiTypeAdapter.notifyDataSetChanged();
            Toast.makeText(BasisActivity.this, "追加数据", Toast.LENGTH_SHORT).show();
        }, 5000);
    }

    public void genDataList(boolean isFirst) {
        if (!isFirst) {
            dataList.add(new HintBean());
        }
        for (int i = 0; i < 20; i++) {
            dataList.add(new BasisImgBean());
            dataList.add(new BasisTextBean(0));
            dataList.add(new BasisTextBean(1));
        }
    }
}
