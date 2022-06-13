package com.quick_example.IMDB_Application;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

    // this class lets us create 1 and only 1 instance for the request queue factory
    // it's a singleton
public class SimpleRequestQueueFactory {
    private static SimpleRequestQueueFactory self = null;
    private RequestQueue queue = null;
    private Context ctx = null;

    private SimpleRequestQueueFactory(Context ctx){
        this.ctx=ctx;
        this.queue = Volley.newRequestQueue(this.ctx);
    }
    public static SimpleRequestQueueFactory getInstance(Context ctx2) throws Exception{

        if (ctx2 == null) {
            throw new Exception ("No Context Found !");
        }
        if (self ==null){
            self = new SimpleRequestQueueFactory(ctx2);
        }
        return self;
    }
    public RequestQueue getRequestInstance() {
        return this.queue;
    }
}
