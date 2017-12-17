package com.rontikeky.mycampus.otpblucampus.RestApi;

import com.rontikeky.mycampus.otpblucampus.Request.PresenceRequest;
import com.rontikeky.mycampus.otpblucampus.Request.RetryOTPRequest;
import com.rontikeky.mycampus.otpblucampus.Request.SigninRequest;
import com.rontikeky.mycampus.otpblucampus.Request.SignupRequest;
import com.rontikeky.mycampus.otpblucampus.Response.DeteailPresenceResponse;
import com.rontikeky.mycampus.otpblucampus.Response.EventResponse;
import com.rontikeky.mycampus.otpblucampus.Response.PresenceResponse;
import com.rontikeky.mycampus.otpblucampus.Response.RetryOTPResponse;
import com.rontikeky.mycampus.otpblucampus.Response.SigninResponse;
import com.rontikeky.mycampus.otpblucampus.Response.SignupResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by Anggit on 07/08/2017.
 */
public interface BlucampusClient {

    @Headers("Accept: application/json")
    @POST("regis_eo")
    Call<SignupResponse> dosignup(@Body SignupRequest signupUserRequest);

    @Headers("Accept: application/json")
    @POST("login")
    Call<SigninResponse> doLogin(@Body SigninRequest loginRequest);

    @Headers("Accept: application/json")
    @POST("retryotp")
    Call<RetryOTPResponse> doRetryOTP(@Body RetryOTPRequest retryOTPRequest);

    @Headers("Accept: application/json")
    @GET("events/{id}")
    Call<EventResponse>   getEvents(@Path("id") String id);

    @Headers("Accept: application/json")
    @GET("events/detail/{id_event}")
    Call<DeteailPresenceResponse>   getDetailEventsPresence(@Path("id_event") String idEvent);

    @Headers("Accept: application/json")
    @POST("events/status")
    Call<PresenceResponse> doPresence(@Body PresenceRequest presenceRequest);

}
