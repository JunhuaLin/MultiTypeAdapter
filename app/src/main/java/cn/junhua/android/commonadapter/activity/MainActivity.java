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
        findViewById(R.id.btn_basis).setOnClickListener(this);
        findViewById(R.id.btn_taobao).setOnClickListener(this);
        findViewById(R.id.btn_one2many).setOnClickListener(this);
        findViewById(R.id.btn_default_view_binder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Class target = null;
        switch (v.getId()) {
            case R.id.btn_basis:
                target = BasisActivity.class;
                break;
            case R.id.btn_one2many:
                target = One2ManyActivity.class;
                break;
            case R.id.btn_taobao:
                target = TaobaoActivity.class;
                break;
            case R.id.btn_default_view_binder:
                target = DefaultViewBinderActivity.class;
                break;
        }
        intent.setClass(this, target);
        startActivity(intent);

    }
}
