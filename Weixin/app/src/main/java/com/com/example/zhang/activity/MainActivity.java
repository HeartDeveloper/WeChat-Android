package com.com.example.zhang.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.com.example.zhang.adapter.PersonAdapter;
import com.com.example.zhang.adapter.TalkAdapter;
import com.com.example.zhang.api.PersonService;
import com.com.example.zhang.model.Classroom;
import com.com.example.zhang.model.Talk;
import com.example.zhang.weixin.R;
import com.facebook.drawee.backends.pipeline.Fresco;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    private PersonAdapter adapter;
    private LinearLayout wx_lin;
    private ImageView wx_img;
    private TextView wx_txt;
    private ImageView more1ImageView2;
    private PopupWindow popupWindow;
    private List <Map <String,Object>>moreimglist;
    private int[]moreimg={R.mipmap.fqql1,R.mipmap.tjpy,R.mipmap.sys,R.mipmap.sfk,
            R.mipmap.bzyfk};
    private String[] moreimgname={"发起群聊","添加朋友","扫一扫","收付款","帮助与反馈"};
    private SimpleAdapter moresim_ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.listView);
        wx_lin= (LinearLayout) findViewById(R.id.wx_lin);
        wx_img= (ImageView) findViewById(R.id.wx_img);
        wx_txt= (TextView) findViewById(R.id.wx_txt);
        more1ImageView2= (ImageView) findViewById(R.id.more1ImageView2);

        View contentView=getLayoutInflater().inflate(R.layout.popuw_item,null);
        ListView morlistview= (ListView) contentView.findViewById(R.id.morelistView);
        moreimglist=new ArrayList<Map<String,Object>>();
        moresim_ada=new SimpleAdapter(this,getmoredata(),R.layout.more_item,new String[]{"moreimg","moreimgname"},new int[]{R.id.moreimage,R.id .moretext}) ;
        morlistview.setAdapter(moresim_ada);


         popupWindow =new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
            //1.创建Retrofit对象
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())//解析方法
                    .baseUrl("http://oawe7zdzm.bkt.clouddn.com/")//主机地址
                    .build();
            //2.创建访问API的请求
            PersonService service = retrofit.create(PersonService.class);
            retrofit2.Call<Classroom> call = service.getResult();
            call.enqueue(new retrofit2.Callback<Classroom>() {
                @Override
                public void onResponse(retrofit2.Call<Classroom> call, retrofit2.Response<Classroom> response) {
                    if (response.isSuccessful()) {
                        Classroom result = response.body();
                        adapter= new PersonAdapter(MainActivity.this,result.data );
                        adapter.setData(result.data);
                        listView.setAdapter(adapter) ;
                    }
                }
                @Override
                public void onFailure(retrofit2.Call<Classroom> call, Throwable t) {
                }
            });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String na=  adapter .dataList.get(position).name ;
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", na);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast .makeText(MainActivity .this ,na ,Toast .LENGTH_LONG ).show();

            }
        });

        more1ImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v,-480,30);
            }
        });

        wx_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wx_img.setBackgroundResource(R.color.colorWhite);
                wx_txt.setBackgroundResource(R.color.colorWhite);
                wx_img.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
                wx_txt.getBackground().setColorFilter(getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    public List<Map <String,Object>> getmoredata() {
        for (int i = 0; i < moreimg.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("moreimg", moreimg[i]);
            map.put("moreimgname", moreimgname[i]);
            moreimglist.add(map);
        }
        return moreimglist;
    }
}









