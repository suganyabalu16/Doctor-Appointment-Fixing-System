package com.kite.student.doctorapp.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login(@Field("username") String username,@Field("password") String password,@Field("type") String type);

    @FormUrlEncoded
    @POST("doctorreg.php")
    Call<ResponseBody> register(@Field("doctorname") String doctorname,@Field("doctoremail") String doctoremail,@Field("password") String password,@Field("spec") String spec,@Field("edu") String edu,@Field("available") String available,@Field("address") String address);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> patientRegister(@Field("name") String name,@Field("email") String email,@Field("password") String password,@Field("city") String city,@Field("address") String address);


    @FormUrlEncoded
    @POST("prescription.php")
    Call<ResponseBody> prescription(@Field("name") String name,@Field("age") String age,@Field("healthissue") String healthissue,@Field("healthdetail") String healthdetail,@Field("prescription") String prescription,@Field("validitydate") String validitydate);


    @FormUrlEncoded
    @POST("listdoctor.php")
    Call<ResponseBody> listDoctor(@Field("doctorid") String doctorid);


    Call<ResponseBody> register(String name, String email, String password, String city, String address);
}
