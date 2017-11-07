package com.example.coco.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mTool)
    Toolbar mTool;
    @BindView(R.id.mLinear)
    LinearLayout mLinear;
    @BindView(R.id.mImg_back)
    ImageView mImgBack;
    @BindView(R.id.mEd)
    EditText mEd;
    @BindView(R.id.mImg_clear)
    ImageView mImgClear;
    @BindView(R.id.mSearch)
    LinearLayout mSearch;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.mCard)
    CardView mCard;
    private ArrayList<String> list;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//butterknife绑定id
        initDate();//初始化数据
        initToolbar();//初始化toolbar
        initListener();//监听事件

    }

    private void initDate() {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        adapter = new MyAdapter(list, new MyAdapter.IListener() {
            @Override
            public void normalItemClick(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clearClick() {
//                list.clear();//清空历史纪录

//                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "清除历史记录", Toast.LENGTH_SHORT).show();
            }
        });
        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        mTool.inflateMenu(R.menu.menu_main);
        mTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItem = item.getItemId();
                switch (menuItem) {
                    case R.id.action_search:
                        SearchUtils.handleToolBar(getApplicationContext(),mCard,mEd);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
    private void initListener() {
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchUtils.handleToolBar(getApplicationContext(),mCard,mEd);
            }
        });
    }
}
