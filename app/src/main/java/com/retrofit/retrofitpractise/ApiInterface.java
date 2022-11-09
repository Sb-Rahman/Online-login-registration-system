package com.retrofit.retrofitpractise;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("signup.php")
    Call<ApiModel> getregister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginresponseModel> getlogin(
            @Field("email") String email,
            @Field("password") String password
    );

}
