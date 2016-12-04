package com.eternallove.demo.mywechat.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentsActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private MyWeChatDB myWeChatDB;
    private static final String url = "";
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
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
            myWeChatDB = MyWeChatDB.getInstance(MomentsActivity.this);
            // Showing progress dialog
            pDialog = new ProgressDialog(MomentsActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//            String jsonStr = sh.makeServiceCall(url);
            String jsonStr = "{\n" +
                    "    \"contacts\": [\n" +
                    "        {\n" +
                    "            \"id\": \"0_0\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"0\",\n" +
                    "            \"name\": \"eternallove\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_1\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c1\",\n" +
                    "            \"name\": \"Angels\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_2\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c2\",\n" +
                    "            \"name\": \"Angel_奏心\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_3\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c3\",\n" +
                    "            \"name\": \"cbc\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_4\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c4\",\n" +
                    "            \"name\": \"cck\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_5\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c5\",\n" +
                    "            \"name\": \"crt\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_6\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c6\",\n" +
                    "            \"name\": \"cxf\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"0_c_7\",\n" +
                    "            \"user_id\": \"0\",\n" +
                    "            \"contact_id\": \"c7\",\n" +
                    "            \"name\": \"DJ\",\n" +
                    "            \"avatar\": \"\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"moment\": [\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_1\",\n" +
                    "            \"user_id\": \"c1\",\n" +
                    "            \"publish_date\": 1480778788866,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {\n" +
                    "                    \"url\": \"http://www.baidu.com\",\n" +
                    "                    \"lingimg\": \"\",\n" +
                    "                    \"linktitle\": \"这是一条百度的链接，不要点，点了也没用\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c2_m_1\",\n" +
                    "            \"user_id\": \"c2\",\n" +
                    "            \"publish_date\": 1480778688866,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"寒蝉凄切对长亭晚，写一点文字凑凑数，android的爱与恨，这是一条纯纯的,我是奏心\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c3_m_1\",\n" +
                    "            \"user_id\": \"c3\",\n" +
                    "            \"publish_date\": 1480776688866,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"飘逸的秀发，无法直视的美，与初级存储万的战斗刚刚结束，我拿到了全新的100TB固态硬盘、强力记忆提升剂一瓶，还有冥狼皮Android战袍一套，这可是难得一遇的好装备呀。你们可能有所不知，初级存储王叫闻见\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_1\",\n" +
                    "            \"user_id\": \"c4\",\n" +
                    "            \"publish_date\": 1480766688866,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"四张图居然是2*2，而不是3+1呢。\",\n" +
                    "                \"imageList\": [\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/39.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/30.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/30.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/30.jpg\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c5_m_1\",\n" +
                    "            \"user_id\": \"c5\",\n" +
                    "            \"publish_date\": 1480666688866,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"我就静静的发张图\",\n" +
                    "                \"imageList\": [\n" +
                    "                    {\n" +
                    "                        \"img\": \"https://s-media-cache-ak0.pinimg.com/564x/a7/7c/11/a77c11589d8be49f9bad3cd52778a176.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://uupaper.oss-cn-qingdao.aliyuncs.com/b6eee5a620d6e14f4f8e5786f24244f7.jpeg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://uupaper.oss-cn-qingdao.aliyuncs.com/b6eee5a620d6e14f4f8e5786f24244f7.jpeg\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c6_m_1\",\n" +
                    "            \"user_id\": \"c6\",\n" +
                    "            \"publish_date\": 1480666666666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"奇妙的九宫格物语\",\n" +
                    "                \"imageList\": [\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/30.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/31.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/31.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/31.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/31.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/31.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/32.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/32.jpg\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/32.jpg\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c3_m_2\",\n" +
                    "            \"user_id\": \"c3\",\n" +
                    "            \"publish_date\": 1478666666666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"一张图会发送什么奇妙的故事，一张图会显示其原来的大小或是长宽等比例缩小\",\n" +
                    "                \"imageList\": [\n" +
                    "                    {\n" +
                    "                        \"img\": \"http://img15.3lian.com/2015/c1/83/d/30.jpg\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c7_m_1\",\n" +
                    "            \"user_id\": \"c7\",\n" +
                    "            \"publish_date\": 1478665666666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"这是一个只能打字的年代，听说是可以发个视频什么的，可是你的流量好像已经枯竭了呢\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c2_m_2\",\n" +
                    "            \"user_id\": \"c2\",\n" +
                    "            \"publish_date\": 1478665666666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"别太天真的，这个点能做什么\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_2\",\n" +
                    "            \"user_id\": \"c4\",\n" +
                    "            \"publish_date\": 1478665566666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"非酋求赞，给大佬递火\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2\",\n" +
                    "            \"user_id\": \"c1\",\n" +
                    "            \"publish_date\": 1478665546666,\n" +
                    "            \"jsonContent\": {\n" +
                    "                \"content\": \"吸欧气，吐非气。\\n你获得一张偷渡船票~\",\n" +
                    "                \"imageList\": [],\n" +
                    "                \"link\": {}\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"like\": [\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_2_c1\",\n" +
                    "            \"moment_id\": \"c4_m_2\",\n" +
                    "            \"user_id\": \"c1\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_2_c2\",\n" +
                    "            \"moment_id\": \"c4_m_2\",\n" +
                    "            \"user_id\": \"c2\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_2_c5\",\n" +
                    "            \"moment_id\": \"c4_m_2\",\n" +
                    "            \"user_id\": \"c5\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c4_m_2_c7\",\n" +
                    "            \"moment_id\": \"c4_m_2\",\n" +
                    "            \"user_id\": \"c7\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_1_c5\",\n" +
                    "            \"moment_id\": \"c1_m_1\",\n" +
                    "            \"user_id\": \"c5\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c6\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"user_id\": \"c6\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c4\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"user_id\": \"c4\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"comment\": [\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c2_to_c1\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"c2\",\n" +
                    "            \"recipient_id\": \"\",\n" +
                    "            \"content\": \"我和你说呀\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c1_to_c2\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"c1\",\n" +
                    "            \"recipient_id\": \"c2\",\n" +
                    "            \"content\": \"你要和我说什么\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c3_to_c2\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"c3\",\n" +
                    "            \"recipient_id\": \"c2\",\n" +
                    "            \"content\": \"我和该条朋友圈共同好友对其中我们另一个共同好友进行了回复\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c1_to_c1\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"c1\",\n" +
                    "            \"recipient_id\": \"\",\n" +
                    "            \"content\": \"所有人统一回复\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_0_to_c1\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"0\",\n" +
                    "            \"recipient_id\": \"\",\n" +
                    "            \"content\": \"我说了一句话\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"c1_m_2_c1_to_0\",\n" +
                    "            \"moment_id\": \"c1_m_2\",\n" +
                    "            \"initiator_id\": \"c1\",\n" +
                    "            \"recipient_id\": \"0\",\n" +
                    "            \"content\": \"测试就到这里吧\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            JsonUtil jsonUtil = new JsonUtil(MomentsActivity.this,jsonStr);
            jsonUtil.refreshSQLite();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            HeadBean headBean = myWeChatDB.loadHead("0");
            List<MomentBean> momentBean = myWeChatDB.loadMoment("0");

            MomentAdapter adapter = new MomentAdapter(MomentsActivity.this,headBean,momentBean);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(
                    new LinearLayoutManager(MomentsActivity.this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setHasFixedSize(true);
        }

    }
}