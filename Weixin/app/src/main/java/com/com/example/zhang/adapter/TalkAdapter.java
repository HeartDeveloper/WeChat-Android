package com.com.example.zhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.com.example.zhang.model.Talk;
import com.example.zhang.weixin.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
public class TalkAdapter extends BaseAdapter {

    public List<Talk> tadataList;
    private LayoutInflater taInflater;

    public TalkAdapter(Context context , List<Talk> data){
        tadataList=data;
        taInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return tadataList.size() ;
    }

    @Override
    public Object getItem(int position) {
        String nafrom=tadataList.get(position).from ;
        return nafrom;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null ;
//        if(convertView==null){
            viewHolder=new ViewHolder() ;
            convertView =taInflater.inflate(R.layout.talk_item,null) ;
            convertView .setTag(viewHolder) ;
            viewHolder.tfAvatar=(SimpleDraweeView)convertView .findViewById(R.id.tfAvatar);
            viewHolder.talkTime=(TextView)convertView .findViewById(R.id .talkTime);
            viewHolder.receiveText=(TextView)convertView .findViewById(R.id.receiveText);
            viewHolder.sendText=(TextView)convertView .findViewById(R.id.sendText);
            viewHolder.ttAvatar=(SimpleDraweeView)convertView .findViewById(R.id.ttAvatar);

//        }else {
//            viewHolder =(ViewHolder)convertView .getTag();
//        }


        viewHolder.talkTime.setText(tadataList .get(position).time );
        if(tadataList .get(position).type.equals("receive")){
            viewHolder.receiveText.setText(tadataList .get(position).text) ;
            viewHolder.sendText.setVisibility(View.GONE);
        }else{
            viewHolder.sendText.setText(tadataList .get(position).text) ;
            viewHolder.receiveText.setVisibility(View.GONE);
        }if (tadataList .get(position).type.equals("receive")){
            viewHolder.tfAvatar.setImageURI(tadataList.get(position ).srcfrom) ;
        }else {
            viewHolder.ttAvatar.setImageURI(tadataList.get(position ).srcto);
        }


        ;
        return convertView;
    }
    public void setData(List<Talk> arrayList) {
        tadataList.addAll(arrayList);
    }
    public static class ViewHolder{
        public SimpleDraweeView tfAvatar,ttAvatar;
        public TextView talkTime,receiveText,sendText;
    }

}
