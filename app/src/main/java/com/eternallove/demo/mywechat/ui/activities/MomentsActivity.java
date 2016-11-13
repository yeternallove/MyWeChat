package com.eternallove.demo.mywechat.ui.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.modle.GeneralBean;
import com.eternallove.demo.mywechat.modle.HeadBean;
import com.eternallove.demo.mywechat.ui.adapters.FriendCircleAdapter;

import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    public static void actionStart(Context context){
        Intent intent=new Intent();
        intent.setClass(context,MomentsActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        FriendCircleAdapter adapter = new FriendCircleAdapter(this,
                new HeadBean(
                        "http://img0.imgtn.bdimg.com/it/u=2075776712,3107953298&fm=21&gp=0.jpg",
                        "http://wenwen.soso.com/p/20110814/20110814160542-1916500023.jpg",
                        "啦啦啦"),
                Arrays.asList(
                        new GeneralBean(
                                "http://img0w.pconline.com.cn/pconline/1309/11/3464151_25.jpg",
                                "cxf",
                                "lala",
                                Arrays.asList(
                                        "http://img0w.pconline.com.cn/pconline/1309/11/3464151_26.jpg"
                                ),
                                new Date(System.currentTimeMillis() - 2000000000)),
                        new GeneralBean(
                                "http://img0w.pconline.com.cn/pconline/1309/11/3464151_26.jpg",
                                "DJ",
                                "lala",
                                Arrays.asList(
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108",
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108"
                                ),
                                new Date()),
                        new GeneralBean(
                                "http://img0w.pconline.com.cn/pconline/1309/11/3464151_26.jpg",
                                "DJ",
                                "lala",
                                Arrays.asList(
                                        "https://s-media-cache-ak0.pinimg.com/564x/a7/7c/11/a77c11589d8be49f9bad3cd52778a176.jpg",
                                        "http://uupaper.oss-cn-qingdao.aliyuncs.com/b6eee5a620d6e14f4f8e5786f24244f7.jpeg",
                                        "http://uupaper.oss-cn-qingdao.aliyuncs.com/b6eee5a620d6e14f4f8e5786f24244f7.jpeg"
                                ),
                                new Date()),
                        new GeneralBean(
                                "http://img0w.pconline.com.cn/pconline/1309/11/3464151_26.jpg",
                                "DJ",
                                "lala",
                                Arrays.asList(
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108",
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108",
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108",
                                        "http://p2.gexing.com/G1/M00/BE/E8/rBACFFP5O-_C8g25AAAcHiXXCRw039_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108",
                                        "http://p1.gexing.com/G1/M00/BE/E8/rBACFFP5O-_Bc-NsAAAdxGiqf-I588_200x200_3.jpg?recache=20131108"
                                ),
                                new Date())

                ));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_moments, menu);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final View v = MomentsActivity.this.findViewById(R.id.action_comment_camera);
                if (v != null) {
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return false;
                        }
                    });
                }
            }
        });
        return true;
    }
}