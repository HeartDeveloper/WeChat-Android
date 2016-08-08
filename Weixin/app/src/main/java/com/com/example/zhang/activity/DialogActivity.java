package com.com.example.zhang.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.com.example.zhang.adapter.MyPagerAdapter;
import com.com.example.zhang.adapter.TalkAdapter;
import com.com.example.zhang.api.TalkService;
import com.com.example.zhang.model.Chatroom;
import com.com.example.zhang.model.Talk;
import com.example.zhang.weixin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DialogActivity extends AppCompatActivity {
    private TextView nametxt;
    private TextView receivetxt;
    private TextView tsendtxt;
    private ImageView imageview ;
    private ImageView add_img;
    private GridView fgridview ;
    private GridView sgridview ;
    private ListView dig_listView;
    public TalkAdapter talk_adapter;
    private String receiv;
    private String sen;
    private ViewPager pager;
    private List<View>viewlist;//将gridview布局转成View对象存到这里作为viewpager的数据源
    private List <Map <String,Object>>imglist1;
    private List <Map <String,Object>>imglist2;

    private int[]img1={R.mipmap.tp  ,R.mipmap.xsp,R.mipmap.hb ,R.mipmap .zz ,
            R.mipmap.sc  ,R.mipmap .wz ,R.mipmap .splt ,R.mipmap .mp  };
    private String[] imgname1={"图片","小视频","红包","转账","我的收藏","位置","视频聊天","名片"};
    private SimpleAdapter sim_ada1;
    private int[]img2={R.mipmap.ic_launcher };
    private String[] imgname2={"语音输入"};
    private SimpleAdapter sim_ada2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //gridview布局转成View对象
        viewlist =new ArrayList<View>();
        //通过view对象作为viewpager的数据源
        View view1=View.inflate(this,R.layout .gridview1 ,null) ;

        View view2=View.inflate(this,R.layout .gridview2 ,null) ;
        viewlist .add(view1);
        viewlist .add(view2);

        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter =new MyPagerAdapter(viewlist) ;//创建MyPagerAdapter的对象
        pager.setAdapter(myPagerAdapter) ;


        nametxt=(TextView ) findViewById(R.id .T);
        receivetxt= (TextView) findViewById(R.id.receiveText);
        tsendtxt= (TextView) findViewById(R.id.sendText);

        receiv="receive";
        sen="send";
        Bundle bundle = this.getIntent().getExtras();
        final String name = bundle.getString("name");
        nametxt.setText(name);


        dig_listView = (ListView) findViewById(R.id.dig_listView );
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl("http://oawe7zdzm.bkt.clouddn.com/")//主机地址
                .build();
        //2.创建访问API的请求
        TalkService service = retrofit.create(TalkService.class);
        retrofit2.Call<Chatroom> call = service.getResult();
        call.enqueue(new retrofit2.Callback<Chatroom>() {
            @Override
            public void onResponse(Call<Chatroom> call, Response<Chatroom> response) {
                if(response.isSuccessful()){
                    Chatroom result = response.body();
                    ArrayList<Talk> list = new ArrayList<Talk>();
                    for(int i=0;i<result.messages.size();i++){
                        result.messages.get(i);
                       if( result.messages.get(i).from.equals(name) ){
                           list.add(result.messages.get(i));
                       }
                    }
                    talk_adapter= new TalkAdapter(DialogActivity.this,list);
                    talk_adapter.setData(list);
                    dig_listView.setSelection(list.size() -1) ;
                    dig_listView.setAdapter(talk_adapter) ;
                }
            }

            @Override
            public void onFailure(Call<Chatroom> call, Throwable t) {

            }
        });

        imageview = (ImageView) findViewById(R.id.dig_ima_bck);
        add_img= (ImageView) findViewById(R.id.add);
        fgridview= (GridView) view1.findViewById(R.id.fgridview );
        sgridview= (GridView) view2.findViewById(R.id.sgridview  );

        imglist1=new ArrayList<Map<String,Object>>();
        sim_ada1=new SimpleAdapter(this,getimgdata1(),R.layout .gridview_item ,new String[]{"img1","text1"},new int[]{R.id .tupian_imag,R.id .imgtext}) ;
        fgridview.setAdapter(sim_ada1) ;

        imglist2=new ArrayList<Map<String,Object>>();
        sim_ada2=new SimpleAdapter(this,getdata(),R.layout .gridview_item2 ,new String[]{"img2","text2"},new int[]{R.id .tupian_imag2,R.id .imgtext2}) ;
        sgridview.setAdapter(sim_ada2) ;


        imageview .setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish() ;
            }
        });

        add_img .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig_listView.setSelection(dig_listView.getBottom());
                if (pager.getVisibility() == View.VISIBLE) {
                    pager.setVisibility(View.GONE);
                }else{
                    pager .setVisibility(View.VISIBLE) ;

                }
            }
        });

    }
    private List <Map <String,Object>> getimgdata1(){
        for(int i=0;i<img1.length ;i++){
            Map <String,Object>map=new  HashMap <String,Object>();
            map.put("img1",img1[i]) ;
            map.put("text1",imgname1[i]) ;
            imglist1.add(map) ;
        }
        return imglist1;

    }
    private List <Map <String,Object>> getdata(){
        for(int i=0;i<img2.length ;i++){
            Map <String,Object>map=new  HashMap <String,Object>();
            map.put("img2",img2[i]) ;
            map.put("text2",imgname2[i]) ;
            imglist2.add(map) ;
        }
        return imglist2;

    }

}
