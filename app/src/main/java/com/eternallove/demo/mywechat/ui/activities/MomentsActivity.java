package com.eternallove.demo.mywechat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.db.MyWeChatDB;
import com.eternallove.demo.mywechat.modle.HeadBean;
import com.eternallove.demo.mywechat.modle.MomentBean;
import com.eternallove.demo.mywechat.ui.adapters.MomentAdapter;
import com.eternallove.demo.mywechat.util.HttpHandler;
import com.eternallove.demo.mywechat.util.JsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentsActivity extends AppCompatActivity {

    private MyWeChatDB myWeChatDB;
    private HeadBean headBean;
    private List<MomentBean> momentBean;
    private MomentAdapter adapter;

    private static final String url = "http://101.200.49.232/eternallove.json";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static void actionStart(Context context){
        Intent intent=new Intent();
        intent.setClass(context,MomentsActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetMoment().execute((Void)null);
            }
        });
        myWeChatDB = MyWeChatDB.getInstance(MomentsActivity.this);
        headBean = myWeChatDB.loadHead("0");
        momentBean = myWeChatDB.loadMoment("0");
        adapter = new MomentAdapter(MomentsActivity.this,headBean,momentBean);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(MomentsActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        new GetMoment().execute((Void)null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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

    private class GetMoment extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSwipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.e("json", "Response from url: " + jsonStr);
            JsonUtil jsonUtil = new JsonUtil(MomentsActivity.this,jsonStr);
            jsonUtil.refreshSQLite();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            headBean = myWeChatDB.loadHead("0");
            momentBean.clear();
            momentBean.addAll(myWeChatDB.loadMoment("0"));
            adapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }
}