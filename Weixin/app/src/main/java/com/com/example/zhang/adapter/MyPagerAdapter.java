package com.com.example.zhang.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Administrator on 2016/8/3.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewlist;
    public MyPagerAdapter (List<View> viewlist){
        this.viewlist =viewlist ;
    }
    @Override
    public int getCount() {
        return viewlist.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewlist .get(position )) ;
        return viewlist .get(position ) ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewlist .get(position) ) ;
    }
}
