package com.com.example.zhang.api;

import com.com.example.zhang.model.Chatroom;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/7/31.
 */
public interface TalkService {
    @GET("chatrooom.json")
    Call<Chatroom> getResult();
}
