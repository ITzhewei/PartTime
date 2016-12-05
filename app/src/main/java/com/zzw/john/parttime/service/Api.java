package com.zzw.john.parttime.service;

import com.zzw.john.parttime.bean.AddBean;
import com.zzw.john.parttime.bean.BaseBean;
import com.zzw.john.parttime.bean.EmployeeBeanAll;
import com.zzw.john.parttime.bean.JobBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Observable<EmployeeBeanAll> login(
            @Query("nickname") String nickname,
            @Query("password") String password
    );

    //得到全部兼职
    @GET("job/queryAllTypeJob")
    Observable<JobBean> queryAllTypeJob(
    );

    //得到我报名过的兼职
    @POST("statusrecord/queryStatusRecordByEmployeeID/{type}")
    Observable<JobBean> queryStatusRecordByEmployeeID(
            @Path("type") Integer type,
            @Query("employeeID") Integer employeeID
    );

    //报名一个兼职
    @POST("statusrecord/add")
    Observable<AddBean> add(
            @Query("employeeID") String employeeID,
            @Query("employerID") Integer employerID,
            @Query("jobID") Integer jobID
    );

    //更改真实姓名
    @POST("employee/update")
    Observable<BaseBean> updateName(
            @Query("id") Integer id,
            @Query("name") String name
    );

    //更改年龄
    @POST("employee/update")
    Observable<BaseBean> updateAge(
            @Query("id") Integer id,
            @Query("age") Integer age
    );

    //更改性别
    @POST("employee/update")
    Observable<BaseBean> updateSex(
            @Query("id") Integer id,
            @Query("sex") String sex
    );

    //更改身高
    @POST("employee/update")
    Observable<BaseBean> updateHeight(
            @Query("id") Integer id,
            @Query("height") Integer height
    );

    //更改所在学校
    @POST("employee/update")
    Observable<BaseBean> updateSchoolName(
            @Query("id") Integer id,
            @Query("schoolName") String schoolName
    );

    //更改电话号码
    @POST("employee/update")
    Observable<BaseBean> updatePhone(
            @Query("id") Integer id,
            @Query("phone") String phone
    );

    //更改兼职意向
    @POST("employee/update")
    Observable<BaseBean> updateIntent(
            @Query("id") Integer id,
            @Query("intent") String intent
    );

    //更改个人评价
    @POST("employee/update")
    Observable<BaseBean> updateAdvantage(
            @Query("id") Integer id,
            @Query("advantage") String advantage
    );
}
