package com.example.joy.sweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joy.sweather.Constract.presenter.AeraPresenter;
import com.example.joy.sweather.Constract.view.IAeraView;
import com.example.joy.sweather.R;
import com.example.joy.sweather.base.BaseFragment;
import com.example.joy.sweather.utils.Canstants;

import java.util.List;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.fragments
 * 简介
 */

public class ChooseAreaFragment extends BaseFragment<IAeraView,AeraPresenter> implements IAeraView, AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView tvTitle;
    private Button btnBack;
    private ListView lvAera;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);

        initView(view);


        return view;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        btnBack = view.findViewById(R.id.btn_back);
        lvAera = view.findViewById(R.id.lv_area);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //初始化显示省级列表
        presenter.onResume(Canstants.provinceAddress);
    }

    //实例化presenter
    @Override
    protected AeraPresenter initPresenter() {
        return new AeraPresenter();
    }



    //更新数据
    @Override
    public void setListItem(List<String> mList) {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,mList);
        lvAera.setAdapter(adapter);
        lvAera.setOnItemClickListener(this);
    }


    //失败信息
    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }


    //点击填充并切换数据
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }




    @Override
    public void setTitle(String text) {
        tvTitle.setText(text);
    }


    @Override
    public void backButtonShow() {
        btnBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void backButtonHide() {
        btnBack.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showDialog() {
        presenter.showProgressDialog(getActivity());
    }

    @Override
    public void hideDialog() {
        presenter.hideProgressDialog();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                presenter.onBackPress();
                break;
        }
    }
}
