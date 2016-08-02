package com.example.qiao.mygit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private List<String> mDatas, mDatas2;
    private MyRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        initData();
        initData2();
        recyclerAdapter = new MyRecyclerAdapter(MainActivity.this, mDatas, mDatas2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
//        recyclerview .setLayoutManager(new GridLayoutManager( this,4));
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
 //设置动画
        recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(recyclerview));
//        recyclerview.addItemDecoration(new DividerItemDecoration(MainActivity.this,
//                DividerItemDecoration.VERTICAL_LIST));
        recyclerAdapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + "long",
                        Toast.LENGTH_SHORT).show();
            }
        });
        recyclerview.setAdapter(recyclerAdapter);
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            mDatas.add("item" + i);
        }
    }

    private void initData2() {
        mDatas2 = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mDatas2.add("button" + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
            recyclerAdapter.addData(1);
                break;
            case R.id.action_delete:
            recyclerAdapter.deleteData(1);
                break;
            case R.id.action_gridview:
                recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_listview:
                recyclerview.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered:
                Intent intent=new Intent(this,StaggeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_hor_gridview:
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(5,
                        StaggeredGridLayoutManager
                        .HORIZONTAL));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
