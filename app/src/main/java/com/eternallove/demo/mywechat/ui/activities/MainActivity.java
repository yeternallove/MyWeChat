package com.eternallove.demo.mywechat.ui.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.ui.fragments.ContactsFragment;
import com.eternallove.demo.mywechat.ui.fragments.DiscoverFragment;
import com.eternallove.demo.mywechat.ui.fragments.MeFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setOverflowShowingAlways();
        int mId=PreferenceManager.getDefaultSharedPreferences(this).getInt("mId",-1);
        if(mId == -1){
            LoginActivity.actionStart(this);
        }
        getFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout_main,new ContactsFragment())
                .commit();
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getFragmentManager();
                switch (item.getItemId()) {
//                    case R.id.bottom_Nav_item_1:
//                        break;
                    case R.id.bottom_Nav_item_2:
                        ContactsFragment confrgm =new ContactsFragment();
                        fm.beginTransaction()
                                .replace(R.id.framelayout_main,confrgm)
                                .commit();
                        break;
                    case R.id.bottom_Nav_item_3:
                        DiscoverFragment disfrgm = new DiscoverFragment();
                        fm.beginTransaction()
                                .replace(R.id.framelayout_main,disfrgm)
                                .commit();
                        break;
                    case R.id.bottom_Nav_item_4:
                        MeFragment mefrgm = new MeFragment();
                        fm.beginTransaction()
                                .replace(R.id.framelayout_main,mefrgm)
                                .commit();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "你点击了“搜索”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_setting:
                Toast.makeText(this, "你点击了“设置”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_synchronous:
                Toast.makeText(this, "你点击了“同步”按键！", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * 出现OverflowShowing图标
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 强制出现OverflowMenu
     */
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}