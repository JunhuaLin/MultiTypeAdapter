package cn.junhua.android.commonadapter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.junhua.android.commonadapter.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.single_recycler_btn).setOnClickListener(this);
        findViewById(R.id.common_recycler_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.single_recycler_btn:
                intent.setClass(this, SingleRecyclerViewAdapterActivity.class);
                break;
            case R.id.common_recycler_btn:
                intent.setClass(this, MultiTypeRecyclerViewAdapterActivity.class);
                break;
        }

        startActivity(intent);

    }
}
