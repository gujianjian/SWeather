package com.example.joy.sweather.fragments;

import android.content.Intent;
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
import com.example.joy.sweather.ui.MainActivity;
import com.example.joy.sweather.ui.WeatherInfoActivity;
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
        presenter.onResume(Canstants.PROVINCE_ADDRESS);
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


    /**
     * 点击填充并切换数据
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }


    /**
     * 启动WeatherInfoActivity
     * @param weatherId weatherId
     */
    @Override
    public void invokeWeatherInfo(String weatherId) {

        //判读是否在天气详细页还是主页

        //在主页则传入weatherId值，然后启动天气详细页
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity= (MainActivity) getActivity();
            Intent intent = new Intent(getActivity(), WeatherInfoActivity.class);
            Bundle data = new Bundle();
            data.putString("weatherId", weatherId);
            intent.putExtras(data);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        } else if (getActivity() instanceof WeatherInfoActivity) {
            WeatherInfoActivity weatherInfoActivity = (WeatherInfoActivity) getActivity();
            weatherInfoActivity.drawer_layout.closeDrawers();
            weatherInfoActivity.srl_refresh.setRefreshing(true);
            weatherInfoActivity.weatherId=weatherId;
            weatherInfoActivity.presenter.resume(weatherId);
        }



    }

    /**
     * 设置标题
     * @param text
     */
    @Override
    public void setTitle(String text) {
        tvTitle.setText(text);
    }


    /**
     * 显示返回箭头button
     */
    @Override
    public void backButtonShow() {
        btnBack.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏返回箭头button
     */
    @Override
    public void backButtonHide() {
        btnBack.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示加载中对话框
     */
    @Override
    public void showDialog() {
        presenter.showProgressDialog(getActivity());
    }

    /**
     * 隐藏加载中对话框
     */
    @Override
    public void hideDialog() {
        presenter.hideProgressDialog();
    }


    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                presenter.onBackPress();
                break;
        }
    }
}
