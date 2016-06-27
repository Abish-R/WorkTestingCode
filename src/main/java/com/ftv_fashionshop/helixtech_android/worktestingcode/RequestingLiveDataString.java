package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

/**
 * Created by helixtech-android on 27/6/16.
 */
public class RequestingLiveDataString {
    ProgressDialog pDialog;
    Context context;
    String valiue;
    public RequestingLiveDataString(Context con){
        context = con;
    }

    public void GetResponseString(){
        // Tag used to cancel the request
        final String tag_json_obj = "string_req";

        String url = "http://synd.cricbuzz.com/j2me/1.0/sch_calender.xml";

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest req = new StringRequest(Request.Method.GET,
                url,
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                valiue= response;
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error response
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
        //return valiue;
    }

    private void stringReturn(){

    }
}
