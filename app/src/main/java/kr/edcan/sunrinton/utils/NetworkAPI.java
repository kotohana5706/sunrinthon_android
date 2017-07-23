package kr.edcan.sunrinton.utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.edcan.sunrinton.models.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    @GET("/auth/fb/authenticate")
    Call<User> loginByFacebook(@Query("access_token") String token);

    @POST("/auth/login")
    @FormUrlEncoded
    Call<User> loginLocal(
            @Field("email") String email,
            @Field("pw") String password);

    @POST("/auth/local/authenticate")
    @FormUrlEncoded
    Call<User> authenticateByToken(
            @Field("token") String token);

    @POST("/auth/register")
    @FormUrlEncoded
    Call<ResponseBody> registerLocal(
            @Field("email") String email,
            @Field("name") String name,
            @Field("pw") String password);


}
