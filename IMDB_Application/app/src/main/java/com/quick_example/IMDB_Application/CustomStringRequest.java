package com.quick_example.IMDB_Application;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CustomStringRequest extends StringRequest {
    private Map<String, String> params = null;
    private Map<String, String> headers = null;
    private String token = "";


    public CustomStringRequest(int method, String url, Map<String, String> params, String token, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.setParams(params);
        this.setToken(token);
    }

    public CustomStringRequest(int method, String url, String token, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.setToken(token);
    }

    private void setToken(String token){
        this.token = token;
    }

    private void setParams(Map<String, String> params){
        this.params = params;
    }

    //get headers
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if(this.headers == null) {
            return headers = new HashMap<String, String>();
        }
        this.headers.put("x-rapidapi-key", token);
        return this.headers;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
