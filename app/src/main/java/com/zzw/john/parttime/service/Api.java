package com.zzw.john.parttime.service;

import com.zzw.john.parttime.bean.BaseBean;
import com.zzw.john.parttime.bean.EmployerBeanAll;
import com.zzw.john.parttime.bean.JobBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by john on 2016/11/1.
 * 所有的 api接口
 */

public interface Api {
    //baseUri
    String API_SERVER_URL = "http://z160e02960.iask.in/webservice/";

    //注册
    @POST("employee/register")
    Observable<BaseBean> register(
            @Query("nickname") String nickname,
            @Query("password") String password
    );
    //登录
    @POST("employee/login")
    Observable<EmployerBeanAll> login(
            @Query("nickname") String nickname,
            @Query("password") String password
    );
    //得到全部兼职
    @GET("jpb/queryAllTypeJob")
    Observable<JobBean> queryAllTypeJob(

    );

}
