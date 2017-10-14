package com.tensun.scalinglayoutdemo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by mertsimsek on 01/10/2017.
 */

public class CollapsingDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");                                                                       // 為了取消官方預設的標題文字
        setSupportActionBar(toolbar);                                                               // 如果要實現setDisplayHomeAsUpEnabled(), 必須要先對toolbar 實體化
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                      // 设置是否添加显示NavigationIcon, 如果沒有另外透過setNavigationIcon() 設置圖片來源的話, 預設是顯示官方的圖案
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
