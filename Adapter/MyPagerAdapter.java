package com.fanxiaoyudemo.magicalwardrobe.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fanxi on 2020/2/3.
 */

public class MyPagerAdapter extends PagerAdapter {
    public static final int MAX_SCROLL_VALUE =10000;
    private List<ImageView>myItems;
    private Context myContext;
    private LayoutInflater myInflater;

    public MyPagerAdapter(List<ImageView>items,Context context){
        myContext=context;
        myInflater=LayoutInflater.from(context);
        myItems=items;
    }

    public Object instantiateItem(ViewGroup container, int position){
        View ret=null;

        //对ViewPager页号求模去除view列表中要显示的项
        position%=myItems.size();
        Log.d("Adapter","instantiateItem:position"+position);
        ret = myItems.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出错误
        ViewParent viewParent = ret.getParent();
        if(viewParent!=null){
            ViewGroup parent = (ViewGroup)viewParent;
            parent.removeView(ret);
        }
        container.addView(ret);

        return ret;
    }
    public int getCount(){
        int ret=0;
        if(myItems.size()>0){
            ret=MAX_SCROLL_VALUE;
        }
        return ret;
    }

    public boolean isViewFromObject(View view,Object object){
        return view==(View)object;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        //警告:不要在这里调用removeView, 已经在instantiateItem中处理了
    }
}
