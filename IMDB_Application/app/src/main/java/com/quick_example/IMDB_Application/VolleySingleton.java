package com.quick_example.IMDB_Application;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// volley singleton
public class VolleySingleton {

    private static VolleySingleton self = null;
    private RequestQueue queue = null;
    private Context ctx = null;

    private VolleySingleton(Context ctx){
        this.ctx = ctx;
        this.queue = Volley.newRequestQueue(this.ctx);
    }

    public static synchronized VolleySingleton getInstance(Context ctx) throws Exception{

        if (ctx == null) {
            throw new Exception ("No Context Found !");
        }
        if (self ==null){
            self = new VolleySingleton(ctx);
        }
        return self;
    }
    public RequestQueue getRequestInstance() {
        return this.queue;
    }
}
