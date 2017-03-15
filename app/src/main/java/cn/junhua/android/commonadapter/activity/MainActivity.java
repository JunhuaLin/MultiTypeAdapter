package cn.junhua.android.commonadapter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.junhua.android.commonadapter.R;

import static cn.junhua.android.commonadapter.R.id.single_base_btn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(single_base_btn).setOnClickListener(this);
        findViewById(R.id.common_base_btn).setOnClickListener(this);
        findViewById(R.id.single_recycler_btn).setOnClickListener(this);
        findViewById(R.id.common_recycler_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case single_base_btn:
                intent.setClass(this, SingleBaseAdapterActivity.class);
                break;
            case R.id.common_base_btn:
                intent.setClass(this, CommonBaseAdapterActivity.class);
                break;
            case R.id.single_recycler_btn:
                intent.setClass(this, SingleRecyclerViewAdapterActivity.class);
                break;
            case R.id.common_recycler_btn:
                intent.setClass(this, CommonRecyclerViewAdapterActivity.class);
                break;
        }

        startActivity(intent);

    }
}
