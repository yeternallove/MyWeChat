package com.eternallove.demo.mywechat.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.ui.activities.MomentsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/8
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_moments)
    Button btn_moments;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_discover,container,false);
        ButterKnife.bind(this,view);
        btn_moments.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_moments:
                Intent intent=new Intent();
                intent.setClass(getActivity(),MomentsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
