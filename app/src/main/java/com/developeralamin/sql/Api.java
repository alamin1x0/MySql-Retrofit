package com.developeralamin.sql;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> createUser(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email
    );
}
