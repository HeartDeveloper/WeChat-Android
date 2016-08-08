package com.com.example.zhang.api;

import com.com.example.zhang.model.Classroom;
import com.com.example.zhang.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface PersonService {
        @GET("classrm.json")
        Call<Classroom> getResult();
    }

