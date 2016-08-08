package com.com.example.zhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.com.example.zhang.model.Person;
import com.example.zhang.weixin.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class PersonAdapter extends BaseAdapter {

    public List<Person> dataList;
    private LayoutInflater mInflater;

    public PersonAdapter(Context context , List<Person> data){
        dataList=data;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return dataList .get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null ;
        if(convertView==null){
            viewHolder=new ViewHolder() ;
            convertView =mInflater.inflate(R.layout.item  ,null) ;
            convertView .setTag(viewHolder) ;
            viewHolder.imgAvatar=(SimpleDraweeView)convertView .findViewById(R.id.imgAvatar);
            viewHolder.txtName=(TextView)convertView .findViewById(R.id .txtName );
            viewHolder.txtText=(TextView)convertView .findViewById(R.id .txtText );
            viewHolder.txtTime=(TextView)convertView .findViewById(R.id.txtTime );
        }else {
            viewHolder =(ViewHolder)convertView .getTag();
        }
        viewHolder.imgAvatar.setImageURI(dataList.get(position ).src) ;
        viewHolder.txtName.setText(dataList .get(position).name );
        viewHolder.txtText.setText(dataList .get(position).text  ) ;
        viewHolder.txtTime.setText(dataList .get(position).time ) ;
        return convertView;
    }
    public void setData(List<Person> arrayList) {
        dataList.addAll(arrayList);
    }


    /**
     * Created by Administrator on 2016/7/30.
     */
    public static class ViewHolder{
        public SimpleDraweeView imgAvatar;
        public TextView txtName,txtText,txtTime;
    }
}
