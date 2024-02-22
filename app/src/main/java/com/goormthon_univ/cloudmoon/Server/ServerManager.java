package com.goormthon_univ.cloudmoon.Server;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.goormthon_univ.cloudmoon.Lang;

import java.util.HashMap;
import java.util.Map;

public class ServerManager {
    //Volley 라이브러리
    static RequestQueue request_queue;
    static String url_main="http://172.30.1.77:8080";
    /*
    http://172.30.1.77:8080
    https://eea0-183-98-102-76.ngrok-free.app
    https://virtserver.swaggerhub.com/eoslovy/cloudmoon/1.0.0
     */

    public ServerManager(Context context){
        if(this.request_queue==null){
            request_queue= Volley.newRequestQueue(context);
        }
    }

    public void string_request_get(String url){
        StringRequest request=new StringRequest(
                Request.Method.GET,
                url_main+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //응답을 받았을 때 동작
                        Log.e("ServerManager","test");
                        Log.e("ServerManager",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //오류가 발생했을 때 동작
                        Log.e("ServerManager","오류");
                        Log.e("ServerManager",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms=new HashMap<String,String>();
                parms.put("email","parms");
                parms.put("password","pass");
                return parms;
            }
        };
        request.setShouldCache(false);
        request_queue.add(request);
    }

    public void string_request_post(String url,Map<String,String> parms_copy){
        //final String[] return_str = {""};

        StringRequest request=new StringRequest(
                Request.Method.POST,
                url_main+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //응답을 받았을 때 동작
                        Log.e("ServerManager",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //오류가 발생했을 때 동작
                        Log.e("ServerManager","오류");
                        Log.e("ServerManager",error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms=new HashMap<String,String>();
                parms=parms_copy;
                return parms;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<String,String>();
                header.put("Content-Type", "application/json");
                //header.put("ngrok-skip-browser-warning", "69420");
                //header.put("ngrok-skip-browser-warning request header", "true");
                //header.put("User-Agent","Mozilla/5.0");
                return header;
            }
        };
        //request.setShouldCache(false);
        //request.setRetryPolicy(new DefaultRetryPolicy(60000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request_queue.add(request);
    }

    public void response_json(String response){
        Gson gson=new Gson();
        Register register=gson.fromJson(response,Register.class);
    }
}
